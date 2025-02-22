package com.rcacao.localsearch.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class JsonHelperImpl @Inject constructor(@ApplicationContext private val context: Context) :
    JsonHelper {

    override fun getJsonDataFromAsset(fileName: String): String? = try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        throw ioException
    }

}