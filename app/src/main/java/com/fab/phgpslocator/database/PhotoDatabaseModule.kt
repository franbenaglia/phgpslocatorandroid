package com.fab.phgpslocator.database

import android.content.Context
import androidx.room.Room
import com.fab.phgpslocator.dao.PhotoDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotoDatabaseModule {
    @Provides
    @Singleton
    fun providePhotoDatabase(@ApplicationContext context: Context): PhotoDatabase {
        return Room.databaseBuilder(
            context,
            PhotoDatabase::class.java,
            "photoData.db"
        ).build()
    }


    @Singleton
    @Provides
    fun providePhotoDetailDao(photoDatabase: PhotoDatabase): PhotoDetailDao =
        photoDatabase.getPhotoDetailDao()

}