package com.example.weatherdome.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherdome.Repository
import com.example.weatherdome.logic.model.Location

class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    var weatherLiveData = Transformations.switchMap(locationLiveData){
        Repository.refreshWeather(it.lng,it.lat)
    }

    fun refreshWeather(lng : String, lat : String){
        locationLiveData.value = Location(lng, lat)
    }
}