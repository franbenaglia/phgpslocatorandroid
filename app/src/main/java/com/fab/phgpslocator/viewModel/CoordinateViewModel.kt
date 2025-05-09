package com.fab.phgpslocator.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fab.phgpslocator.CoordinateDataStore
import com.fab.phgpslocator.entity.Coordinate
import kotlinx.coroutines.launch

// todo leer https://medium.com/@riyaspullurofficial/what-is-datastore-in-android-with-kotlin-jetpack-compose-45820cf8f41b
// sin viewmodel https://proandroiddev.com/using-android-jetpack-datastore-with-jetpack-compose-6184338cf9c0

//https://github.com/cp-megh-l/BiometricAuthenticationCompose VER ESTE CODIGO TIENE VIEMODEL CON
//DATASTORE Y HILT

class CoordinateViewModel(private val context: Context) : ViewModel() {

    private val dataStore = CoordinateDataStore(context)

    init {

    }

    val coordinates = dataStore.coordinates

    fun saveCoordinate(coordinate: Coordinate) {
        viewModelScope.launch {
            dataStore.saveCoordinate(coordinate)
        }
    }

    fun deleteCoordinates() {
        viewModelScope.launch {
            dataStore.deleteCoordinates()
        }
    }
}