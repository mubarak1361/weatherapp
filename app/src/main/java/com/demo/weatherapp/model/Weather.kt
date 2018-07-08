package com.demo.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Weather(@SerializedName("main")
                   val weather:WeatherDetail,
                   @SerializedName("wind")
                   val wind: Wind)