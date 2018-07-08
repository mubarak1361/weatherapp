package com.demo.weatherapp.helper

import com.demo.weatherapp.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Mubarak Mohideen.
 */

class WeatherClientBuilder private constructor() {

    fun getClient(): WeatherClient = client

    companion object {

        private var clientBuilder: WeatherClientBuilder? = null
        private lateinit var client: WeatherClient

        fun getInstance(): WeatherClientBuilder {

            if (clientBuilder == null) {

                clientBuilder = WeatherClientBuilder()

                val httpClient = OkHttpClient.Builder()

                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    httpClient.addInterceptor(interceptor)
                    httpClient.addNetworkInterceptor(StethoInterceptor())
                }

                httpClient.addInterceptor(InternetConnectivityInterceptor())
                httpClient.addInterceptor(ReceiveAndAddCookiesInterceptor())

                /** Read and connect time out set to 5 minutes  */
                /** Retry to call API  */
                httpClient.connectTimeout(5, TimeUnit.MINUTES)
                httpClient.readTimeout(5, TimeUnit.MINUTES)
                httpClient.retryOnConnectionFailure(true)

                val gson = GsonBuilder().setLenient().create()

                val builder = Retrofit.Builder().baseUrl(BuildConfig.END_POINT)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                val retrofit = builder.client(httpClient.build()).build()

                client = retrofit.create(WeatherClient::class.java)

            }

            return clientBuilder!!
        }
    }
}