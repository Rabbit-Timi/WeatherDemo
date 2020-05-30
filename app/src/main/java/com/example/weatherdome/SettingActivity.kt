package com.example.weatherdome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.weatherdome.logic.model.Place
import com.example.weatherdome.logic.model.Setting
import com.example.weatherdome.ui.place.PlaceViewModel
import com.example.weatherdome.ui.setting.SettingViewModel
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProvider(this).get(SettingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val setting = viewModel.getSavedSetting()
        if(setting != null){
            day_night_remind.isChecked = setting.dayNightRemind
            nightUpdate.isChecked = setting.nightUpdate
        }

        backBtn.setOnClickListener {
            val place = ViewModelProvider(this).get(PlaceViewModel::class.java).getSavedPlace()
            val intent = Intent(this, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
        }
        day_night_remind.setOnClickListener {
            val anrStatus = day_night_remind.isChecked()
            val nuStatus = nightUpdate.isChecked()
            viewModel.saveSetting(Setting(anrStatus,nuStatus))
        }
        nightUpdate.setOnClickListener {
            val anrStatus = day_night_remind.isChecked()
            val nuStatus = nightUpdate.isChecked()
            viewModel.saveSetting(Setting(anrStatus,nuStatus))
        }
    }
}
