package com.company.doria.api

import android.content.SharedPreferences
import com.company.doria.utilities.DoriaConstants.ACCESS_TOKEN
import com.company.doria.utilities.preferences.PreferenceHelper.get
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HttpHeaderInterceptor @Inject constructor(private val preferences: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val accessToken: String? = preferences[ACCESS_TOKEN]
        proceed(
                request()
                        .newBuilder()
                        .addHeader("Authorization", accessToken!!)
                        .build()
        )
    }
}