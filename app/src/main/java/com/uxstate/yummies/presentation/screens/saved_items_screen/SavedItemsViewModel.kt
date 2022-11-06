package com.uxstate.yummies.presentation.screens.saved_items_screen

import androidx.lifecycle.ViewModel
import com.uxstate.yummies.domain.use_cases.UseCaseContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SavedItemsViewModel @Inject constructor(
    private val container: UseCaseContainer
) : ViewModel() {
        

}