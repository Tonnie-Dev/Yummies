package com.uxstate.yummies.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.data.remote.dto.MealsResponseDTO
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.details_screen.details_event.DetailsScreenEvent
import com.uxstate.yummies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val container: UseCaseContainer) :
    ViewModel() {


    //meal listing status

    private val _starredStatus = MutableStateFlow(false)
    val starredStatus = _starredStatus.asStateFlow()

    //list of starred meals
    private val _starredMeals = MutableStateFlow<List<Meal>>(emptyList())



    //private val _mealsList = MutableStateFlow<List<Meal>>(emptyList())
    //private val _currentMealAsPerDatabase = MutableStateFlow(false)
   // val currentMealAsPerDatabase = _currentMealAsPerDatabase.asStateFlow()

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

    private fun getStarredMeals(){

        container.getStarredMeals().onEach {

            _starredMeals.value = it
        }.launchIn(viewModelScope)
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