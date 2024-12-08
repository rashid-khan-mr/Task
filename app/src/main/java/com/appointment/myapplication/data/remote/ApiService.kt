package com.appointment.myapplication.data.remote

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
  //  @GET("5d8b1d61-360b-409f-a15d-6e5237d06f0a/") //
    @GET("16da2d41-7bc3-4dcc-872c-59107248dcca/") //
    suspend fun getMedicationsString(): String

    @GET("6f6e424f-3743-4457-9cda-a4370e6d6446/") //
  suspend fun getMedications(): ResponseBody

    @GET("6f6e424f-3743-4457-9cda-a4370e6d6446/") //
    suspend fun getMedi(): Response<ResponseBody>

//    @GET("5d8b1d61-360b-409f-a15d-6e5237d06f0a/") //
//    suspend fun getMedi(): ResponseBody
}