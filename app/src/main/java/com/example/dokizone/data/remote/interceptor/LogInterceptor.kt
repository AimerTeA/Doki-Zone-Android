package com.example.dokizone.data.remote.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class LogInterceptor : Interceptor {
    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("LogInterceptor", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return loggingInterceptor.intercept(chain)
    }
}