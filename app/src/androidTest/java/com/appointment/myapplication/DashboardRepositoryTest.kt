package com.appointment.myapplication

import com.appointment.myapplication.data.local.RoomDAO
import com.appointment.myapplication.data.model.DrugInfo
import com.appointment.myapplication.data.remote.ApiService
import com.appointment.myapplication.repository.DashboardRepository
import kotlinx.coroutines.runBlocking
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class DashboardRepositoryTest {

    private val roomDAO: RoomDAO = mock(RoomDAO::class.java)
    private val apiService: ApiService = mock(ApiService::class.java)
    private val dashboardRepository = DashboardRepository(roomDAO, apiService)


    @Test
    fun saveDrugs(): Unit = runBlocking {
        val drugsList = listOf(DrugInfo(5,"asprin","","500mg"), DrugInfo(6,"asprin","","100mg"))

        dashboardRepository.saveDrugsInDB(drugsList)

        verify(roomDAO).saveDrugs(drugsList)
    }
}