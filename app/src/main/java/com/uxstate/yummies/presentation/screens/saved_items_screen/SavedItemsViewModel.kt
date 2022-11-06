package com.uxstate.yummies.presentation.screens.saved_items_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class SavedItemsViewModel @Inject constructor(
    private val container: UseCaseContainer
) : ViewModel() {

    var savedMeals by mutableStateOf<List<Meal>>(emptyList())
        private set

    init {
        getSavedMeals()
    }

    fun onEvent(event: SavedScreenEvent) {

        when (event) {

            is SavedScreenEvent.DeleteMeal -> {

                viewModelScope.launch {

                    container.unStarUseCase(event.meal)
                }
            }
        }
    }

    private fun getSavedMeals() {

        viewModelScope.launch {
            container.getStarredMeals()
                .collectLatest {
                    savedMeals = it
                }
        }
    }
}