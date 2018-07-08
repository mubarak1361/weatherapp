package com.demo.weatherapp.helper

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

/**
 * Created by Mubarak Mohideen.
 */

class ReceiveAndAddCookiesInterceptor : Interceptor {

    private var cookies: HashSet<String>? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (cookies != null) {

            val builder = chain.request().newBuilder()

            for (cookie in cookies!!) {
                builder.addHeader("Cookie", cookie)
                Log.v("OkHttp", "Adding Header: $cookie")
            }
            return chain.proceed(builder.build())

        } else {

            val originalResponse = chain.proceed(chain.request())

            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                val cookies = HashSet<String>()

                for (header in originalResponse.headers("Set-Cookie")) {
                    cookies.add(header)
                }
                this.cookies = cookies
            }
            return originalResponse
        }

    }
}