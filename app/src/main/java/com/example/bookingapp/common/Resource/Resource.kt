package com.example.bookingapp.common.Resource

import okhttp3.ResponseBody


sealed class Resource<out T> {

    data class Success<out T>(val value: T) : Resource<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorResponse: ResponseBody?
    ) : Resource<Nothing>()

    data class Loading(
        val success: Boolean,
        val error: Boolean,
        val loading: Boolean
    ) : Resource<Nothing>()
}