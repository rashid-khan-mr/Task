package com.appointment.myapplication.di

import com.appointment.myapplication.data.local.RoomDAO
import com.appointment.myapplication.data.remote.ApiService
import com.appointment.myapplication.repository.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class )
object RepositoryModule {

    @Provides
    fun provideDashboardRepository(
        roomDAO: RoomDAO,
        apiService: ApiService
    ): DashboardRepository {
        return DashboardRepository(  roomDAO, apiService)
    }

}