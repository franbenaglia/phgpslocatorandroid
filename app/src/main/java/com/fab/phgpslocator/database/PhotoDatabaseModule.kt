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
//https://medium.com/@ramadan123sayed/simple-guide-to-hilt-dependency-injection-in-android-with-jetpack-compose-and-ksp-3ddcbfaad37d
//https://medium.com/@atharvapajgade/dagger-hilt-with-kotlin-and-jetpack-compose-26eb394f5130
// en providePhotoDetailDao se inyecta providePhotoDatabase o esa seria la idea hay algun requisito con respecto a nombre de funcion?
// lo mismo para llamadas a providePhotoDetailDao (se llama desde databaseviewmodel)
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