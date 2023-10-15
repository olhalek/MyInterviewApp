package com.example.myinterviewapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myinterviewapp.model.DataObject
import com.example.myinterviewapp.repository.GifRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val gifRepository: GifRepository): ViewModel() {

    private val _gifsList: MutableLiveData<List<DataObject>> = MutableLiveData()
    val gifsList: LiveData<List<DataObject>> = _gifsList

    fun getGifs() {
        viewModelScope.launch(Dispatchers.IO) {
            val gifs = gifRepository.getGifs()

            if (gifs.isPresent) {
                withContext(Dispatchers.Main) {
                    _gifsList.value = gifs.get()
                }
            }
        }
    }

    fun getGifUrl(position:Int): String {
        return _gifsList.value?.get(position)?.images?.originalImage?.url ?: ""
    }
}