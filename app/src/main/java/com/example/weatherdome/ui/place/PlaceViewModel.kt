package com.example.weatherdome.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherdome.Repository
import com.example.weatherdome.logic.model.Place

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData){query ->
        Repository.searchPlaces(query)
    }
    fun searchPlaces(query : String){
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavedPlace() = Repository.getSavedPlace()
    fun isSavedPlace() = Repository.isPlaceSaved()
}