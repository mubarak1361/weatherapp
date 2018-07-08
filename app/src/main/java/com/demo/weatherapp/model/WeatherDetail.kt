package com.demo.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherDetail(@SerializedName("temp")
                         val temperature: String,
                         @SerializedName("pressure")
                         val pressure: String,
                         @SerializedName("humidity")
                         val humidity: String,
                         @SerializedName("temp_min")
                         val minimumTemperature: String,
                         @SerializedName("temp_max")
                         val maximumTemperature: String)