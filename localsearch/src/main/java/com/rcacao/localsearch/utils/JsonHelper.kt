package com.rcacao.localsearch.utils

import android.content.Context
import java.io.IOException

class JsonHelper constructor(private val context: Context) {

    fun getJsonDataFromAsset(fileName: String): String? = try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        throw ioException
    }

}