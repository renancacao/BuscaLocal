package com.rcacao.localsearch.data.datasource

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rcacao.localsearch.data.NullProviderListException
import com.rcacao.localsearch.data.model.Provider
import com.rcacao.localsearch.utils.JsonHelper
import java.lang.reflect.Type
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val jsonHelper: JsonHelper) : DataSource {

    override fun getProviders(): List<Provider> {
        val jsonString: String? = jsonHelper.getJsonDataFromAsset("providers.json")
        if (jsonString != null) {
            val listProviderType: Type = object : TypeToken<List<Provider>>() {}.type
            return Gson().fromJson(jsonString, listProviderType)
        } else {
            throw NullProviderListException()
        }
    }

}