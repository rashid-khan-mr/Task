package com.appointment.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.appointment.myapplication.data.model.DrugInfo
import com.appointment.myapplication.data.remote.Resource
import com.appointment.myapplication.data.remote.Status
import com.appointment.myapplication.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext

@HiltViewModel
class DashboadViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _drugInfoList = MutableStateFlow<List<DrugInfo>>(emptyList())
    val drugInfoList: StateFlow<List<DrugInfo>> = _drugInfoList.asStateFlow()

    init {
        fectchProblems()
    }


    fun fectchProblems(){
        CoroutineScope(Dispatchers.IO).launch {
            postDDSalesSummery().collectLatest {
                withContext(Dispatchers.Main) {
                    Log.v("calling", " result" + it)
                    _drugInfoList.value = it.data ?: emptyList()
                }

                it.data?.let { drugslist->
                    dashboardRepository.saveDrugsInDB(drugslist)
                }
            }
        }

    }

    suspend fun postDDSalesSummery(): Flow<Resource<List<DrugInfo>>> {
        Log.v("calling"," postDD")
        return dashboardRepository.postDDSalesSummery1().transform {
            when (it.status) {
                Status.LOADING -> {
                    Log.v("hahahaa loading","loading")
                    emit(Resource.loading(null))
                }

                Status.SUCCESS -> {
                    emit(Resource.success(data = it.data))
                }
                Status.ERROR -> {
                    Log.v("hahahaa  error","error"+it.message)
                    if (it.statusCode == 401) {
                        emit(Resource.error("${it.message}", it.statusCode, null))

                    } else {
                        emit(Resource.error("" + it.message, it.statusCode, null))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}