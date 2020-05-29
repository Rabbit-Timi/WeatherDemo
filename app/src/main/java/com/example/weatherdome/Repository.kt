package com.example.weatherdome

import androidx.lifecycle.liveData
import com.example.weatherdome.logic.model.Place
import com.example.weatherdome.logic.model.Weather
import com.example.weatherdome.logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
    //刷新天气信息
    fun refreshWeather(lng : String, lat : String) = liveData(Dispatchers.IO) {
        val result = try{
            //创建协程作用域通过coroutineScope
            coroutineScope {
                val deferredRealtime = async {
                    WeatherNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    WeatherNetwork.getDailyWeather(lng, lat)
                }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                    val weather = Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure<Weather>(
                        RuntimeException("realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}")
                    )
                }
            }
        }catch (e : Exception){
            Result.failure<Weather>(e)
        }
        emit(result)
    }
}