package com.uxstate.yummies.util

sealed class Resource<T>(val data: T? = null, val errorMessage: String? = null) {

    // Error - attach some data in case we need to show stale data
    class Error<T>(data: T?, errorMessage: String) : Resource<T>(data, errorMessage)

    // Success
    class Success<T>(data: T?) : Resource<T>(data = data)

    // Loading
    class Loading<T>(val loading: Boolean = true) : Resource<T>()
}
