package com.uxstate.yummies.presentation.screens.overview_screen.overview_events

sealed class OverviewEvent(){

    data class OnSearchQueryChange(val text:String):OverviewEvent()
    object OnRefresh:OverviewEvent()
    object OnClearText:OverviewEvent()
}