package com.fab.phgpslocator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fab.phgpslocator.entity.PhotoDetail

@Dao
interface PhotoDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photoDetail: PhotoDetail)

    @Query("Select * From photoDetail Where id = :id")
    suspend fun getPhotoDetailById(id: Int): PhotoDetail?

    @Query("DELETE FROM photoDetail Where id = :id")
    suspend fun deletePhotoDetailById(id: Int)

    @Query("SELECT * FROM photoDetail")
    suspend fun getAllPhotoDetails(): List<PhotoDetail?>
}