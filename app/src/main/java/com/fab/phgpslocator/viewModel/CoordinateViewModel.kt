package com.fab.phgpslocator.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fab.phgpslocator.CoordinateDataStore
import com.fab.phgpslocator.entity.Coordinate
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoordinateViewModel(private val context: Context) : ViewModel() {

    init {
        loadSavedCoordinates(context)
    }

    private val _savedCoordinates = mutableStateOf<Array<Coordinate>?>(null)
    val savedCoordinates: State<Array<Coordinate>?> = _savedCoordinates

    private fun loadSavedCoordinates(context: Context) {
        viewModelScope.launch {
            val dataStore = CoordinateDataStore(context)
            _savedCoordinates.value = dataStore.coordinatesModel.stateIn(
                scope = viewModelScope
            ).value
        }
    }


}