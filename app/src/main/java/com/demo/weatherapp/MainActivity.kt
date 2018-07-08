package com.demo.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.demo.weatherapp.helper.WeatherClientBuilder
import com.demo.weatherapp.model.Weather

class MainActivity : AppCompatActivity() {

    private val weatherClient = WeatherClientBuilder.getInstance().getClient()
    private val weathers:ArrayList<Weather> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var response = weatherClient.getWeather("chennai","metric").execute()
        if(response.isSuccessful && response.body()!=null){
            weathers.add(response.body()!!)
        }

        response = weatherClient.getWeather("mumbai","metric").execute()
        if(response.isSuccessful && response.body()!=null){
            weathers.add(response.body()!!)
        }

        response = weatherClient.getWeather("banglore","metric").execute()
        if(response.isSuccessful && response.body()!=null){
            weathers.add(response.body()!!)
        }

        response = weatherClient.getWeather("new delhi","metric").execute()
        if(response.isSuccessful && response.body()!=null){
            weathers.add(response.body()!!)
        }


    }
}
