package com.fab.phgpslocator.viewModel

import androidx.lifecycle.ViewModel
import com.fab.phgpslocator.model.FormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    fun updateTitle(name: String) {
        val errors = mutableListOf<String>()
        if (name.length < 4) {
            errors.add("The title must be at least 4 characters.")
        }
        _uiState.value = _uiState.value.copy(
            title = name,
            titleErrors = errors
        )
    }

    fun updateDescription(name: String) {
        val errors = mutableListOf<String>()
        if (name.length < 4) {
            errors.add("The description must be at least 4 characters.")
        }
        _uiState.value = _uiState.value.copy(
            description = name,
            descriptionErrors = errors
        )
    }

}