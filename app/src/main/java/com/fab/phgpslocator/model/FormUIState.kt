package com.fab.phgpslocator.model

data class FormUiState(
    val currentName: String = "",
    val currentNameErrors: MutableList<String> = mutableListOf(),
)