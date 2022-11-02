package com.uxstate.yummies.presentation.screens.details_screen

import androidx.lifecycle.ViewModel
import com.uxstate.yummies.domain.repository.YummiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel  @Inject constructor(repository: YummiesRepository) : ViewModel() {
}