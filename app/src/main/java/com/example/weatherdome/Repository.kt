package com.example.weatherdome

import androidx.lifecycle.liveData
import com.example.weatherdome.logic.model.Place
import com.example.weatherdome.logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {
    fun searchPlaces(query : String) = liveData(Dispatchers.IO) {
        val result = try{
            val placeResponse = WeatherNetwork.searchPlaces(query)
            if(placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure<List<Place>>(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e : Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}