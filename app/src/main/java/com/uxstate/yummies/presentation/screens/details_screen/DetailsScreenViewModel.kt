package com.uxstate.yummies.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.details_screen.details_event.DetailsScreenEvent
import com.uxstate.yummies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

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

                    container.updateStarUseCase(
                            meal = event.meal,
                            newStarStatus = !event.meal.isFavorite
                    )
                }
            }
        }
    }


    private fun getMealsList() {

        viewModelScope.launch {

            val list = container.getMealsUseCase(query = "", fetchFromRemote = false)
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