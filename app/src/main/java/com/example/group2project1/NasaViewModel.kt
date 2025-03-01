package com.example.group2project1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData

class NasaViewModel : ViewModel() {
    private val repository = NasaRepository()
    val apodData = MutableLiveData<APODModel?>()
    val errorMessage = MutableLiveData<String>()

    fun fetchAPOD(date: String) {
        viewModelScope.launch {
            try {
                val apod = repository.getAPOD(date)
                apodData.postValue(apod)
            } catch (e: Exception) {
                errorMessage.postValue("Error: ${e.message}")
            }
        }
    }
}