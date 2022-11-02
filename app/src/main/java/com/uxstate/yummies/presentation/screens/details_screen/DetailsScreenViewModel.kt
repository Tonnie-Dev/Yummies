package com.uxstate.yummies.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.details_screen.details_event.DetailsScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val container: UseCaseContainer) :
    ViewModel() {


    fun onEvent(event: DetailsScreenEvent) {

        when (event) {

            is DetailsScreenEvent.OnStarMeal -> {

                viewModelScope.launch {

                    container.updateStarUseCase(
                            meal = event.meal,
                            newStarStatus = !event.meal.isFavorite
                    )
                }
            }
        }
    }
}