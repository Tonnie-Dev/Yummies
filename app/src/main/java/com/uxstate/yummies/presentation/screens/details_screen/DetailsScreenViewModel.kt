package com.uxstate.yummies.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.details_screen.details_event.DetailsScreenEvent
import com.uxstate.yummies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val container: UseCaseContainer) :
    ViewModel() {

    val _mealsList = MutableStateFlow<List<Meal>>(emptyList())

    init {
        getMealsList()
    }

    fun onEvent(event: DetailsScreenEvent) {

        when (event) {

            is DetailsScreenEvent.OnStarMeal -> {

                viewModelScope.launch {

                    val parsedMeal = event.meal
                    val matchedLiveMeal = _mealsList.value.find { it.id == parsedMeal.id }!!
                    container.updateStarUseCase(
                        meal = event.meal,
                        newStarStatus = !matchedLiveMeal.isFavorite
                    )
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
}