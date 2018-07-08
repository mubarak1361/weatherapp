package com.demo.weatherapp.helper

import com.demo.weatherapp.BuildConfig
import com.demo.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherClient{

    @GET("/data/2.5/weather?appid=${BuildConfig.API_KEY}")
    fun getWeather(@Query("q") city: String,@Query("units") units:String): Call<Weather>

}