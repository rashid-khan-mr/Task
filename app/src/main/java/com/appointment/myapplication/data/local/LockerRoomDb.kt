package com.appointment.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appointment.myapplication.data.local.RoomDAO
import com.appointment.myapplication.data.model.DrugInfo

@Database(
    entities =
    [
        DrugInfo::class
    ],
    version = 2,
    exportSchema = false
)
abstract class LockerRoomDb : RoomDatabase() {
    abstract fun roomDAO(): RoomDAO
}