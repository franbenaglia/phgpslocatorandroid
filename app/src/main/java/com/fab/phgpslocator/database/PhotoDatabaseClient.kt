package com.fab.phgpslocator.database

import android.content.Context
import androidx.room.Room

class DatabaseClient(private val context: Context) {

    val appDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            PhotoDatabase::class.java,
            "photoData.db"
        ).build()
    }
}