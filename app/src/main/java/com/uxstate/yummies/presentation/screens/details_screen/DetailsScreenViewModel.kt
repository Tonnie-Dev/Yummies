package com.uxstate.yummies.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.details_screen.details_event.DetailsScreenEvent
import com.uxstate.yummies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val container: UseCaseContainer) :
    ViewModel() {

    private val _mealsList = MutableStateFlow<List<Meal>>(emptyList())
    private val _currentMealAsPerDatabase = MutableStateFlow(false)
    val currentMealAsPerDatabase = _currentMealAsPerDatabase.asStateFlow()

    init {
        getMealsList()
    }

    fun onEvent(event: DetailsScreenEvent) {

        when (event) {

            is DetailsScreenEvent.OnStarMeal -> {

                viewModelScope.launch {

                    val parsedMeal = event.meal
                    val matchedLiveMeal = _mealsList.value.find { it.id == parsedMeal.id }!!

                    // update DB 1
                    container.updateStarUseCase(
                            meal = matchedLiveMeal, newStarStatus = true
                    )

                    // insert DB 2
                    container.starUseCase(event.meal)
                }
            }

            is DetailsScreenEvent.UnStarMeal -> {

                viewModelScope.launch {
                    val parsedMeal = event.meal
                    val matchedLiveMeal = _mealsList.value.find { it.id == parsedMeal.id }!!

                    // update DB 1
                    container.updateStarUseCase(
                            meal = matchedLiveMeal, newStarStatus = false
                    )

                    // insert DB 2
                    container.unStarUseCase(event.meal)
                }
            }
        }
    }

    private fun getMealsList() {

        viewModelScope.launch {

            container.getMealsUseCase(query = "", fetchFromRemote = false)
                    .collectLatest { result ->
                        when (result) {

                            is Resource.Success -> {

                                result.data?.let {

                                    _mealsList.value = it
                                }
                            }
                            else -> Unit
                        }
                    }
        }
    }

    fun checkStarredStatus(meal: Meal) {

        viewModelScope.launch {

            _currentMealAsPerDatabase.value = container.checkStarredStatusUseCase(meal)
        }


    }
}