package com.wiserax.zodiac.ui.horoscope

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HoroscopeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Horoscope Fragment"
    }
    val text: LiveData<String> = _text
}