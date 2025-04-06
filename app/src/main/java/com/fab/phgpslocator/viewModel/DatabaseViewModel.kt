package com.fab.phgpslocator.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fab.phgpslocator.dao.PhotoDetailDao
import com.fab.phgpslocator.entity.PhotoDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val photoDetailDao: PhotoDetailDao,
) : ViewModel() {

    private val _photoDetail = MutableStateFlow<List<PhotoDetail?>>(arrayListOf())
    val photoDetail get() = _photoDetail.asStateFlow()

    fun photoDetailsFromDB() {
        viewModelScope.launch {
            _photoDetail.value = photoDetailDao.getAllPhotoDetails()
        }
    }

    fun photoDetailFromDB(photoId: Int) {
        viewModelScope.launch {
            photoDetailDao.getPhotoDetailById(photoId)
        }
    }

    fun removePhotoFromDB(photoId: Int) {
        viewModelScope.launch {
            photoDetailDao.deletePhotoDetailById(photoId)
        }
    }

    fun insertPhotoDetailFromDB(photo: PhotoDetail) {
        viewModelScope.launch {
            photoDetailDao.insert(photo)
        }
    }
}