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

    // meal starred status
    private val _starredStatus = MutableStateFlow(false)
    val starredStatus = _starredStatus.asStateFlow()

    // list of starred meals
    private val _starredMeals = MutableStateFlow<List<Meal>>(emptyList())
    val starredMeals = _starredMeals.asStateFlow()

    init {
        getStarredMeals()
    }

    fun onEvent(event: DetailsScreenEvent) {

        when (event) {

            is DetailsScreenEvent.OnStarMeal -> {

                Timber.i("StarEvent felt")
                viewModelScope.launch {
                    // insert DB 2
                    container.starUseCase(event.meal)
                    //  checkStarredStatus(event.meal)
                }
            }

            is DetailsScreenEvent.UnStarMeal -> {
                Timber.i("UnStarEvent passed")
                viewModelScope.launch {

                    // remove from DB 2
                    container.unStarUseCase(event.meal)
                    // checkStarredStatus(event.meal)
                }
            }
        }
    }

    private fun getStarredMeals() {

        container.getStarredMeals().onEach {

            _starredMeals.value = it
        }.launchIn(viewModelScope)
    }

    fun checkStarredStatus(meal: Meal) {
        Timber.i("inside checkStarredStatus()")
        container.checkStarredStatusUseCase(meal).onEach {
            Timber.i("Inside checkStarredStatus - value is: $it")
            _starredStatus.value = it
        }.launchIn(viewModelScope)
    }
}