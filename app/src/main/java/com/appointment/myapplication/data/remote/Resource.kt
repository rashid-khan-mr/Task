package com.appointment.myapplication.data.remote

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val statusCode:Int?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }
        fun <T> error(msg: String, statusCode:Int?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg, statusCode)
        }
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
