package com.appointment.myapplication.di

import android.content.Context
import androidx.room.Room
import com.appointment.myapplication.data.local.LockerRoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRoomDatabase(
        @ApplicationContext app: Context,
    ) = Room.databaseBuilder(
        app,
        LockerRoomDb::class.java,
        "TaniaDeliveryRoomDatabase"
    ).fallbackToDestructiveMigration()
        .enableMultiInstanceInvalidation()
        .allowMainThreadQueries()
        .build() // The reason we can construct a database for the repo


    @Singleton
    @Provides
    @JvmStatic
    fun provideDao(database: LockerRoomDb) = database.roomDAO()
}