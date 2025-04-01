package com.fab.phgpslocator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fab.phgpslocator.dao.PhotoDetailDao
import com.fab.phgpslocator.entity.PhotoDetail

@Database(version = 1, entities = [PhotoDetail::class], exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun getPhotoDetailDao(): PhotoDetailDao
}