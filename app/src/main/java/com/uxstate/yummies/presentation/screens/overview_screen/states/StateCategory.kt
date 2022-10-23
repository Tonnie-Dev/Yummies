package com.uxstate.yummies.presentation.screens.overview_screen.states

import com.uxstate.yummies.domain.model.Category

data class StateCategory (val isLoading:Boolean, val categories:List<Category>)