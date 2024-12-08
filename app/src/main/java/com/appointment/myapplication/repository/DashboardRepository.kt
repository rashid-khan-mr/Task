package com.appointment.myapplication.repository

import android.util.Log
import com.appointment.myapplication.data.local.RoomDAO
import com.appointment.myapplication.data.model.*
import com.appointment.myapplication.data.remote.ApiService
import com.appointment.myapplication.data.remote.Resource
import com.appointment.myapplication.data.remote.Status
import com.appointment.myapplication.data.remote.networkOnly
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform

class DashboardRepository @Inject constructor(
    private val roomDAO: RoomDAO,
    private val apiService: ApiService
) {


    suspend fun getDrugsInfo(): Flow<Resource<List<DrugInfo>>> {

        return networkOnly<List<DrugInfo>>(
            fetch = { apiService.getMedi() },
            onFetchFailed = {
                it.printStackTrace()
            }
        ).transform { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    val drugInfoList = resource.data
                    emit(Resource.success(data = drugInfoList))
                }
                Status.ERROR -> {
                    emit(Resource.error(resource.message ?: "Unknown error", resource.statusCode, null))
                }
                Status.LOADING -> emit(Resource.loading(null))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun saveDrugsInDB(drugsList: List<DrugInfo>)= roomDAO.saveDrugs(drugsList)



}