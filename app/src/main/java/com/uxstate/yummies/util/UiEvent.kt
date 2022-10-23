package com.uxstate.yummies.util

sealed class UiEvent (val error:String){

    object ShowSnackbar:UiEvent("")
}