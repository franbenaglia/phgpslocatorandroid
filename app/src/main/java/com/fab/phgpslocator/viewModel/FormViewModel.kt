package com.fab.phgpslocator.viewModel

import androidx.lifecycle.ViewModel
import com.fab.phgpslocator.model.FormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormViewModel: ViewModel() {
    // Main UI state
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        val errors = mutableListOf<String>()
        if (name.length < 4) {
            errors.add("The name must be at least 4 characters.")
        }
        _uiState.value = _uiState.value.copy(
            currentName = name,
            currentNameErrors = errors
        )
    }
}