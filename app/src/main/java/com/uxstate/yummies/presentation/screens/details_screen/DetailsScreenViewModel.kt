package com.uxstate.yummies.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.details_screen.details_event.DetailsScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val container: UseCaseContainer) :
    ViewModel() {

    fun onEvent(event: DetailsScreenEvent) {

        when (event) {

            is DetailsScreenEvent.OnStarMeal -> {


                viewModelScope.launch {
                    // insert DB 2
                    container.starUseCase(event.meal)
                    //  checkStarredStatus(event.meal)
                }
            }

            is DetailsScreenEvent.UnStarMeal -> {

                viewModelScope.launch {

                    // remove from DB 2
                    container.unStarUseCase(event.meal)
                    // checkStarredStatus(event.meal)
                }
            }
        }
    }




}