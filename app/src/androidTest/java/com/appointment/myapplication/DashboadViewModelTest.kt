package com.appointment.myapplication


import org.mockito.Mockito

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appointment.myapplication.data.model.DrugInfo
import com.appointment.myapplication.data.remote.Resource
import com.appointment.myapplication.repository.DashboardRepository
import com.appointment.myapplication.viewmodel.DashboadViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboadViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dashboardRepository: DashboardRepository = Mockito.mock(DashboardRepository::class.java)
    private val viewModel = DashboadViewModel(dashboardRepository)

    /*@Test
    fun sd() = runBlockingTest {
        // Given
        val drugInfoList = listOf(DrugInfo(1,"Drug1","","500mg"), DrugInfo(2,"Drug2","","100mg"))
        val resource = Resource.success(drugInfoList)
        when(dashboardRepository.postDDSalesSummery1()).t
        // When
        viewModel.fectchProblems()

        // Then
        assertEquals(drugInfoList, viewModel.drugInfoList.value)
    }*/
}