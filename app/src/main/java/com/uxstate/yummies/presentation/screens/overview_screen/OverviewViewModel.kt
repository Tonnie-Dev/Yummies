package com.uxstate.yummies.presentation.screens.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.overview_screen.states.StateCategories
import com.uxstate.yummies.util.Resource
import com.uxstate.yummies.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class OverviewViewModel @Inject constructor(private val container: UseCaseContainer) : ViewModel() {

    val query by mutableStateOf("")
    val fetchFromRemote by mutableStateOf(false)

    private val _stateCategories = MutableStateFlow(StateCategories())
    val stateCategory = _stateCategories.asStateFlow()

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals = _meals.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()



    private fun getCategories() {

        viewModelScope.launch {

            container.getCategoriesUseCase()
                .collectLatest {

                    result ->

                    when (result) {

                        is Resource.Loading -> {

                            _stateCategories.value =
                                StateCategories().copy(isLoading = result.loading)
                        }
                        is Resource.Error -> {
                            val errorMessage = result.errorMessage ?: "Unknown Error"
                            _uiEvent.emit(UiEvent.ShowSnackbar(errorMessage))
                        }
                        is Resource.Success -> {

                            result.data?.let {
                                _stateCategories.value = StateCategories().copy(categories = it)
                            }
                        }
                    }
                }
        }
    }
}