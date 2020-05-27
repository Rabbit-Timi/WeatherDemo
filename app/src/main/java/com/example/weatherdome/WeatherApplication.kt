package com.example.weatherdome

import android.app.Application
import android.content.Context

class WeatherApplication : Application() {

    companion object{
        lateinit var context: Context
        const val TOKEN = "IpLRslsH0D3H9zPD"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}