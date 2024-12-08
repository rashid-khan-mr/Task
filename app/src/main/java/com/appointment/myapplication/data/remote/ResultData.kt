package com.appointment.myapplication.data.remote

sealed class ResultData<out T> {
    data class Loading(val nothing: Nothing? = null): ResultData<Nothing>()
    data class Success<out T>(val data: T? = null, val status: String? = null): ResultData<T>()
    data class Failed(val status: String? = null, val message: String? = null): ResultData<Nothing>()
}