package com.example.weatherdome.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherdome.logic.Repository
import com.example.weatherdome.logic.model.Location
import com.example.weatherdome.logic.model.Place
import com.example.weatherdome.logic.model.Setting

class SettingViewModel : ViewModel() {
    var dayNightRemind = false
    var nightUpdate = false

    fun saveSetting(setting : Setting){
        Repository.saveSetting(setting)
        dayNightRemind = setting.dayNightRemind
        nightUpdate = setting.nightUpdate
    }
    fun getSavedSetting() : Setting? {
        val setting = Repository.getSavedSetting()
        if(setting != null){
            dayNightRemind = setting.dayNightRemind
            nightUpdate = setting.nightUpdate
        }

        return setting
    }
    fun isSettingSaved() = Repository.isSettingSaved()
}