package com.appointment.myapplication.di

import com.appointment.myapplication.data.remote.ApiService
import com.appointment.myapplication.data.remote.NetworkingConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class )
object NetworkModule {

    @Provides
    @javax.inject.Singleton
    @JvmStatic
    fun provideConverterFactory():
            Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @javax.inject.Singleton
    @JvmStatic
    fun providesBaseUrl(): String {
        return NetworkingConstants.BASE_URL
    }

    /********************************************************/
    /*Retrofit Client provider*/
    /*********************************************************/

    @Provides
    @javax.inject.Singleton
    @JvmStatic
    fun provideRetrofitClient(
        baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        val gson = GsonBuilder()
            .setLenient() // Enables lenient parsing
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Use the lenient Gson
            .build()
    }

    /********************************************************/
    /*Api Service provider*/
    /*********************************************************/

    @Provides
    @javax.inject.Singleton
    @JvmStatic
    fun provideRestApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

