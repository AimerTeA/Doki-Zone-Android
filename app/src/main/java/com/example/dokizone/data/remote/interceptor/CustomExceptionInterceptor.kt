package com.example.dokizone.data.remote.interceptor

import com.example.dokizone.core.BadRequestException
import com.example.dokizone.core.NotFoundException
import com.example.dokizone.core.ServerErrorException
import com.example.dokizone.data.remote.model.anime.ErrorResponse
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response

class CustomExceptionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            throw createCustomException(response)
        }

        return response
    }

    private fun createCustomException(response: Response): Exception {
        val errorResponse = Json.decodeFromString<ErrorResponse>(response.body?.string().toString())
        return when (response.code) {
            400 -> BadRequestException(errorResponse.messages?.id?.firstOrNull().orEmpty())
            404 -> NotFoundException(errorResponse.message.orEmpty())
            // Add more custom exception mappings here based on HTTP status codes
            in 500..599 -> ServerErrorException(errorResponse.message.orEmpty())
            else -> Exception("Unknown error")
        }
    }
}