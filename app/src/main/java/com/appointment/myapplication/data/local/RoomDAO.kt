package com.appointment.myapplication.data.local

import androidx.room.*
import com.appointment.myapplication.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDrugs(drugsList: List<DrugInfo>)

}
