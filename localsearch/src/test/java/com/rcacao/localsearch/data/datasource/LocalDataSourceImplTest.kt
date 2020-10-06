package com.rcacao.localsearch.data.datasource

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rcacao.localsearch.data.NullProviderListException
import com.rcacao.localsearch.data.model.Provider
import com.rcacao.localsearch.utils.JsonHelper
import org.junit.Assert
import org.junit.Test
import org.mockito.exceptions.base.MockitoException

class LocalDataSourceImplTest {

    private val jsonHelper: JsonHelper = mock()
    private lateinit var localDataSource: DataSource

    @Test
    fun getProvidersIsSuccess() {
        whenever(jsonHelper.getJsonDataFromAsset(any())).thenReturn(
            "[{\"features\": [\"Radiologia\",\"Laboratorial\",\"Tomografia\"],\"id\": \"1\"," +
                    "\"type\": \"laboratory\",\"name\": \"A+ Morumbi\",\"specialties\": [\"Radiologia\"" +
                    ",\"Laboratorial\",\"Tomografia\"],\"sub_specialties\": [],\"addresses\":" +
                    " [{\"title\": \"Principal\",\"address\": \"Av. Dr. Guilherme Dumont Vilares, 1143," +
                    " Morumbi, São Paulo - 05640-001\"}],\"simplified_addresses\": [{\"title\": \"Principal\"," +
                    "\"address\": \"Av. Dr. Guilherme Dumont Vilares, 1143, Morumbi, São Paulo - 05640-001\"}]," +
                    "\"site\": \"www.amaissaude.com.br\",\"phones\": [],\"working_hours\": {\"always_open\": false," +
                    "\"weekday_text\": [\"Segunda-feira: 06:30 - 18:00\",\"Terça-feira: 06:30 - 18:00\"," +
                    "\"Quarta-feira: 06:30 - 18:00\",\"Quinta-feira: 06:30 - 18:00\",\"Sexta-feira: 06:30 - 18:00\"," +
                    "\"Sábado: 06:30 - 18:00\",\"Domingo: 06:30 - 12:00\"]},\"qualifications\": []," +
                    "\"image_url\": \"https://alice-member-app-assets.s3.amazonaws.com/placeholder/providers/placeholder_lab.png\"," +
                    "\"education\": []},{\"features\": [\"Laboratorial\",\"Tomografia\"],\"id\": \"2\",\"type\": \"laboratory\"," +
                    "\"name\": \"Fleury - Avenida Brasil\",\"specialties\": [\"Laboratorial\",\"Tomografia\"],\"sub_specialties\": []," +
                    "\"addresses\": [{\"title\": \"Principal\",\"address\": \"Av. Brasil, 1891, Jardim Paulista, São Paulo - 01431-001\"}]," +
                    "\"simplified_addresses\": [{\"title\": \"Principal\",\"address\": \"Av. Brasil, 1891, Jardim Paulista, " +
                    "São Paulo - 01431-001\"}],\"site\": \"www.fleury.com.br\",\"phones\": [],\"working_hours\": {\"always_open\":" +
                    " false,\"weekday_text\": [\"Segunda-feira: 06:00 - 18:30\",\"Terça-feira: 06:00 - 18:30\",\"Quarta-feira: " +
                    "06:00 - 18:30\",\"Quinta-feira: 06:00 - 18:30\",\"Sexta-feira: 06:00 - 18:30\",\"Sábado: 06:00 - 12:30\"," +
                    "\"Domingo: 06:30 - 12:30\"]},\"qualifications\": [],\"image_url\":" +
                    " \"https://alice-member-app-assets.s3.amazonaws.com/placeholder/providers/placeholder_lab.png\",\"education\": []}]"
        )
        localDataSource = LocalDataSourceImpl(jsonHelper)

        val result: List<Provider> = localDataSource.getProviders()

        Assert.assertEquals(2, result.size)
        Assert.assertEquals("1", result[0].id)
        Assert.assertEquals("A+ Morumbi", result[0].name)
        Assert.assertEquals("2", result[1].id)
        Assert.assertEquals("Fleury - Avenida Brasil", result[1].name)
    }

    @Test(expected = NullProviderListException::class)
    fun getProvidersReturnNull() {
        whenever(jsonHelper.getJsonDataFromAsset(any())).thenReturn(null)
        localDataSource = LocalDataSourceImpl(jsonHelper)
        localDataSource.getProviders()
    }

    @Test(expected = MockitoException::class)
    fun getProviderThrowsException() {
        whenever(jsonHelper.getJsonDataFromAsset(any())).thenThrow(MockitoException("test"))
        localDataSource = LocalDataSourceImpl(jsonHelper)
        localDataSource.getProviders()
    }
}