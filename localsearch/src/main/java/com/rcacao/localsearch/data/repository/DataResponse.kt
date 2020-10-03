package com.rcacao.localsearch.data.repository

sealed class DataResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : DataResponse<T>()
    data class Failure(val exception: Exception) : DataResponse<Nothing>()
}