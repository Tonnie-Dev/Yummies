package com.uxstate.yummies.presentation.screens.overview_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.overview_screen.overview_events.OverviewEvent
import com.uxstate.yummies.presentation.screens.overview_screen.states.StateCategories
import com.uxstate.yummies.presentation.screens.overview_screen.states.StateMeals
import com.uxstate.yummies.util.Constants.SEARCH_TRIGGER_DELAY
import com.uxstate.yummies.util.Resource
import com.uxstate.yummies.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class OverviewViewModel @Inject constructor(private val container: UseCaseContainer) : ViewModel() {

    private val _stateCategories = MutableStateFlow(StateCategories())
    val stateCategory = _stateCategories.asStateFlow()

    private val _stateMeals = MutableStateFlow(StateMeals())
    val stateMeals = _stateMeals.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var searchJob: Job? = null

    fun onEvent(event: OverviewEvent) {

        when (event) {

            is OverviewEvent.OnClearText -> {

                _stateMeals.value = StateMeals().copy(searchQuery = "")
            }
            is OverviewEvent.OnSearchQueryChange -> {

                // cancel any existing job
                searchJob?.cancel()

                // re-initialize the job
                searchJob = viewModelScope.launch {

                    delay(SEARCH_TRIGGER_DELAY)

                    // execute search after the delay
                    getMeals(query = event.text)
                }
            }
            is OverviewEvent.OnRefresh -> {

                _stateMeals.value = StateMeals().copy(isLoading = true)
            }
        }
    }
    private fun getMeals(query: String = "", fetchFromRemote: Boolean = false) {

        viewModelScope.launch {

            container.getMealsUseCase(
                query = query,
                fetchFromRemote = fetchFromRemote
            )
                .collectLatest {

                    result ->

                    when (result) {

                        is Resource.Success -> {

                            result.data?.let {

                                _stateMeals.value = StateMeals().copy(meals = it)
                            }
                        }
                        is Resource.Error -> {

                            _uiEvent.emit(
                                UiEvent.ShowSnackbar(
                                    result.errorMessage ?: "Unknown Error"
                                )
                            )
                        }
                        is Resource.Loading -> {

                            _stateMeals.value = StateMeals().copy(isLoading = true)
                        }
                    }
                }
        }
    }

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