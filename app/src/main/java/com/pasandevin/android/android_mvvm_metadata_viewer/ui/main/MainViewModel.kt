package com.pasandevin.android.android_mvvm_metadata_viewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasandevin.android.android_mvvm_metadata_viewer.DevByteService
import com.pasandevin.android.android_mvvm_metadata_viewer.Models.DevByteVideo
import com.pasandevin.android.android_mvvm_metadata_viewer.Models.asDatabaseModelFromDev
import com.pasandevin.android.android_mvvm_metadata_viewer.Models.asDomainModel
import com.pasandevin.android.android_mvvm_metadata_viewer.VideosDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val devByteService: DevByteService
) : ViewModel() {

    private val _playlist = MutableLiveData<List<DevByteVideo>>()
    val playlist: LiveData<List<DevByteVideo>>
        get() = _playlist

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() = viewModelScope.launch {
        try {
            val playlist = devByteService.getPlaylist()
            _playlist.postValue(playlist.asDomainModel())
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        } catch (networkError: IOException) {
            _eventNetworkError.value = true
        }
    }

//    fun insertToDB(db: VideosDatabase) {
//        db.VideoDao().insertAll(_playlist.value!!.asDatabaseModelFromDev())
//    }

}