package com.example.dokizone.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

suspend inline fun <T> CoroutineDispatcher.safeCall(crossinline block: suspend () -> T): Result<T> {
    return withContext(this) {
        try {
            Result.Success(block())
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}