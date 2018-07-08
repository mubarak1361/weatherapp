package com.demo.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Wind(@SerializedName("speed")
                val speed: String,
                @SerializedName("deg")
                val degree: String)