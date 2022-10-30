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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val container: UseCaseContainer) : ViewModel() {

    private val _stateCategories = MutableStateFlow(StateCategories())
    val stateCategory = _stateCategories.asStateFlow()

    private val _stateMeals = MutableStateFlow(StateMeals())
    val stateMeals = _stateMeals.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var searchJob: Job? = null

    init {
        getCategories()
        getMeals()
    }

    fun onEvent(event: OverviewEvent) {

        when (event) {

            is OverviewEvent.OnClearText -> {

                _stateMeals.value = _stateMeals.value.copy(searchQuery = "")

                // re-initiate search

                getMeals()
            }

            is OverviewEvent.OnSearchQueryChange -> {

                // update query value
                _stateMeals.value = _stateMeals.value.copy(searchQuery = event.text)

                // cancel any existing job
                searchJob?.cancel()

                // re-initialize the job
                searchJob = viewModelScope.launch {

                    delay(SEARCH_TRIGGER_DELAY)

                    // called only after a delay of 500 ms
                    getMeals()
                }
            }

            is OverviewEvent.OnCategoryClick -> {

                // collect flow meal item
                container.getMealsByCategoryUseCase(event.category)
                        .onEach {
                            _stateMeals.value = _stateMeals.value.copy(meals = it)
                        }
                        .launchIn(viewModelScope)
            }

            is OverviewEvent.OnRefresh -> {

                getMeals(fetchFromRemote = true)
            }
            is OverviewEvent.OnToggleCategoryPanel -> {

                _stateCategories.value =
                    _stateCategories.value.copy(
                            isShowCategories = !_stateCategories.value.isShowCategories
                    )

            }
        }
    }

    private fun getMeals(
        query: String = _stateMeals.value.searchQuery,
        fetchFromRemote: Boolean = false
    ) {

        viewModelScope.launch {

            container.getMealsUseCase(
                    query = query,
                    fetchFromRemote = fetchFromRemote
            )
                    .collectLatest {

                        result ->

                        when (result) {
                            is Resource.Loading -> {

                                _stateMeals.value =
                                    _stateMeals.value.copy(isLoading = result.isLoading)
                            }
                            is Resource.Success -> {

                                result.data?.let {

                                    _stateMeals.value = _stateMeals.value.copy(meals = it)
                                }
                            }
                            is Resource.Error -> {

                                _uiEvent.emit(
                                        UiEvent.ShowSnackbar(
                                                result.errorMessage ?: "Unknown Error"
                                        )
                                )
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
                                    _stateCategories.value.copy(isLoading = result.isLoading)
                            }
                            is Resource.Error -> {
                                val errorMessage = result.errorMessage ?: "Unknown Error"
                                _uiEvent.emit(UiEvent.ShowSnackbar(errorMessage))
                            }
                            is Resource.Success -> {

                                result.data?.let {
                                    _stateCategories.value =
                                        _stateCategories.value.copy(categories = it)
                                }
                            }
                        }
                    }
        }
    }
}