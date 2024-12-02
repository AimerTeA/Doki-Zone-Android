package com.example.dokizone.core

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
}

inline fun <T> Result<T>.addOnSuccessListener(onSuccess: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        onSuccess(data)
    }
    return this
}

inline fun <T> Result<T>.addOnFailureListener(onFailure: (Exception) -> Unit): Result<T> {
    if (this is Result.Error) {
        onFailure(exception)
    }
    return this
}