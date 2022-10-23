package com.uxstate.yummies.util

sealed class UiEvent(val error: String) {

    data class ShowSnackbar(val errorMessage: String) : UiEvent(error = errorMessage)
}