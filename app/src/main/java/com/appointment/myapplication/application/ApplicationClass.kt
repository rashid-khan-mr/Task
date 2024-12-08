package com.appointment.myapplication.application

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
    }
    init {
        instance = this
    }


    companion object {
        lateinit var instance: ApplicationClass

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}