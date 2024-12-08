package com.appointment.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrugInfo(

    @PrimaryKey
    val id : Int,
    val name: String,
    val dose: String,
    val strength: String
)