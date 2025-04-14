package com.example.dokizone.core

import android.util.Log
import com.example.dokizone.core.network.NetworkManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

suspend inline fun <T> CoroutineDispatcher.safeCall(crossinline block: suspend () -> T): Result<T> {
    return withContext(this) {
        try {
            Result.Success(block())
        } catch (e: IOException) {
            Log.e("Error", "addOnFailureListener: $e")
            Result.Error(e)
        } catch (e: Exception) {
            Log.e("Error", "addOnFailureListener: $e")
            Result.Error(e)
        }
    }
}

suspend inline fun <T> CoroutineDispatcher.safeCallWithNetworkCheck(
    networkManager: NetworkManager,
    crossinline remoteCall: suspend () -> T,
    crossinline localCall: suspend () -> T,
    crossinline saveToLocalCall: suspend (data: T) -> Unit
): Result<T> {
    return safeCall {
        if (networkManager.isInternetAvailable()) {
            val data = remoteCall()
            saveToLocalCall(data)
            data
        } else {
            localCall()
        }
    }
}