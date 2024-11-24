package com.example.network.result


sealed class Resource<out T> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    data class Fail(val message: String) : Resource<Nothing>()
}
