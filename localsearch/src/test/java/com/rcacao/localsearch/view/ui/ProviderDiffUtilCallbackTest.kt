package com.rcacao.localsearch.view.ui

import com.google.gson.Gson
import com.rcacao.localsearch.data.model.Provider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProviderDiffUtilCallbackTest {


    private lateinit var diffUtils: ProviderDiffUtilCallback

    @Before
    fun setup() {
        val gson = Gson()
        val provider1: Provider =
            gson.fromJson("{\"name\":\"Test 2\", \"id\":\"1\"}", Provider::class.java)
        val provider2: Provider =
            gson.fromJson("{\"name\":\"Test 3\", \"id\":\"2\"}", Provider::class.java)
        val provider3: Provider =
            gson.fromJson("{\"name\":\"Test 3\", \"id\":\"3\"}", Provider::class.java)
        val oldList: List<Provider> = listOf(provider1, provider2, provider3)
        val newList: List<Provider> = listOf(provider1, provider3)
        diffUtils = ProviderDiffUtilCallback(oldList, newList)
    }

    @Test
    fun getOldListSize() {
        assertEquals(3, diffUtils.oldListSize)
    }

    @Test
    fun getNewListSize() {
        assertEquals(2, diffUtils.newListSize)
    }

    @Test
    fun areItemsTheSame() {
        assertTrue(diffUtils.areItemsTheSame(0, 0))
        assertTrue(diffUtils.areItemsTheSame(2, 1))
        assertFalse(diffUtils.areItemsTheSame(1, 1))
    }

    @Test
    fun areContentsTheSame() {
        assertTrue(diffUtils.areContentsTheSame(0, 0))
        assertTrue(diffUtils.areContentsTheSame(2, 1))
        assertTrue(diffUtils.areContentsTheSame(1, 1))

    }


}