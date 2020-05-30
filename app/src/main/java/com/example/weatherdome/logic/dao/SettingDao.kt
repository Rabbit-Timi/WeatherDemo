package com.example.weatherdome.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.weatherdome.WeatherApplication
import com.example.weatherdome.logic.model.Place
import com.example.weatherdome.logic.model.Setting
import com.google.gson.Gson

object SettingDao {
    private fun sharedPreferences() = WeatherApplication.context.getSharedPreferences("weather_demo",
        Context.MODE_PRIVATE)

    fun saveSetting(setting : Setting){
        sharedPreferences().edit{
            putString("setting", Gson().toJson(setting))
        }
    }

    fun getSetting(): Setting? {
        val settingJson = sharedPreferences().getString("setting",null)
        return Gson().fromJson(settingJson, Setting::class.java)
    }

    fun isSettingSaved() = sharedPreferences().contains("setting")
}