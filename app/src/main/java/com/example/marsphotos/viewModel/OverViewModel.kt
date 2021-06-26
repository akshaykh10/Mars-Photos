package com.example.marsphotos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch


/**
 * [MarsApiStatus] holds the constants which we can use to track the API service call
 */
enum class MarsApiStatus{
    LOADING,
    ERROR,
    DONE
}

class OverViewModel : ViewModel(){

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus> = _status

    private val _photos= MutableLiveData<List<MarsPhoto>>()

    val photos: LiveData<List<MarsPhoto>> = _photos
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhoto()
    }



    private fun getMarsPhoto(){
        viewModelScope.launch {

            _status.value = MarsApiStatus.LOADING

            try{
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value= MarsApiStatus.DONE

            }catch(e:Exception){
                _status.value=MarsApiStatus.ERROR

            }

        }
    }

}