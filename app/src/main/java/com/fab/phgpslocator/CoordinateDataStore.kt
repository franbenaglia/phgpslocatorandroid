package com.fab.phgpslocator

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fab.phgpslocator.entity.Coordinate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
//leer https://medium.com/simform-engineering/introduction-to-kotlin-flow-f425b5a839f
class CoordinateDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("CoordinateDataStore")
        private val COORDINATES = stringPreferencesKey("coordinates")
    }

    val coordinates: Flow<Array<Coordinate>> =
        context.dataStore.data.map { preferences ->
            val c = preferences[COORDINATES] ?: ""
            Json.decodeFromString<Array<Coordinate>>(c)
        }

    suspend fun deleteCoordinates() {
        context.dataStore.edit { preferences ->
            preferences[COORDINATES] = ""
        }
    }

    suspend fun saveCoordinate(coordinate: Coordinate) {
        context.dataStore.edit { preferences ->
            val c: String = preferences[COORDINATES] ?: ""
            var co: Array<Coordinate> = emptyArray<Coordinate>();
            if (c.isNotEmpty()) {
                co = Json.decodeFromString<Array<Coordinate>>(c)
                co += coordinate
            }
            val jsonString =
                if (co.isEmpty()) Json.encodeToString(arrayOf(coordinate)) else Json.encodeToString(co)
            preferences[COORDINATES] = jsonString
        }
    }

}

