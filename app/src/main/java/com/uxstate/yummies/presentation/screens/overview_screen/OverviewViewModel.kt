package com.uxstate.yummies.presentation.screens.overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.yummies.domain.model.Meal
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import com.uxstate.yummies.presentation.screens.overview_screen.states.StateCategory
import com.uxstate.yummies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val container: UseCaseContainer) : ViewModel() {


    val query by mutableStateOf("")
    val fetchFromRemote by mutableStateOf(false)

    private val _stateCategory = MutableStateFlow(StateCategory())
    val stateCategory = _stateCategory.asStateFlow()

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals = _meals.asStateFlow()

    private fun getCategories(){

      viewModelScope.launch {

          container.getCategoriesUseCase().collectLatest {

              result ->

              when(result) {

                  is Resource.Loading -> {

                      _stateCategory.value = StateCategory().copy(isLoading = result.loading)
                  }
                  is Resource.Error -> {

                    _stateCategory.value = StateCategory().copy(error = )
                  }
                  is Resource.Success -> {}
              }

          }
      }

    }

}