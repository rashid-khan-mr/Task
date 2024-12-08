package com.appointment.myapplication.repository

import android.util.Log
import com.appointment.myapplication.data.local.RoomDAO
import com.appointment.myapplication.data.model.*
import com.appointment.myapplication.data.remote.ApiService
import com.appointment.myapplication.data.remote.Resource
import com.appointment.myapplication.data.remote.Status
import com.appointment.myapplication.data.remote.networkOnly
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import java.io.StringReader

class DashboardRepository @Inject constructor(
    private val roomDAO: RoomDAO,
    private val apiService: ApiService
) {


    suspend fun postDDSalesSummery1(): Flow<Resource<List<DrugInfo>>> {
        Log.v("calling", "networking")

        return networkOnly<List<DrugInfo>>(
            fetch = { apiService.getMedi() },
            onFetchFailed = {
                it.printStackTrace()
            }
        ).transform { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    Log.v("calling success ", "ohoo")
                    val drugInfoList = resource.data
                    emit(Resource.success(data = drugInfoList))
                }
                Status.ERROR -> {
                    Log.e("calling", "Error: ${resource.message}")
                    emit(Resource.error(resource.message ?: "Unknown error", resource.statusCode, null))
                }
                Status.LOADING -> emit(Resource.loading(null))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun saveDrugsInDB(drugsList: List<DrugInfo>)= roomDAO.saveDrugs(drugsList)



}