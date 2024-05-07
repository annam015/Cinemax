package com.example.cinemax.ui.cinema

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CinemaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is cinema Fragment"
    }
    val text: LiveData<String> = _text
}