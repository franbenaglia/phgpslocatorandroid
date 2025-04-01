package com.fab.phgpslocator.model

data class FormUiState(

    val title: String = "",
    val titleErrors: MutableList<String> = mutableListOf(),
    val description: String = "",
    val descriptionErrors: MutableList<String> = mutableListOf(),

)