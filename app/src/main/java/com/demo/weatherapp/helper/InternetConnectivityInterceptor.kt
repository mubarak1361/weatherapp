package com.demo.weatherapp.helper


import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Mubarak Mohideen.
 */

class InternetConnectivityInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (Network.isOnline) {
            return chain.proceed(chain.request())
        }
        throw NoConnectivityException()
    }

    inner class NoConnectivityException : IOException() {
        override val message: String?
            get() = "No network available, please check your WiFi or Data connection"
    }
}