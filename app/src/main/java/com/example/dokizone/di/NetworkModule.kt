package com.example.dokizone.di

import com.example.dokizone.BuildConfig
import com.example.dokizone.data.remote.interceptor.CustomExceptionInterceptor
import com.example.dokizone.data.remote.interceptor.LogInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(LogInterceptor())
            .addInterceptor(CustomExceptionInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.JIKAN_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json; charset=UTF8".toMediaType())
            )
            .build()
    }
}