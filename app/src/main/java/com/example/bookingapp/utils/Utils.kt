package com.example.bookingapp.utils

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

inline fun <reified T> getJsonDataFromAsset(context: Context, fileName: String): T? {
    val jsonString: String
    try {
        jsonString =
            context.assets.open("api-response/$fileName").bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return Gson().fromJson(jsonString, T::class.java)
}