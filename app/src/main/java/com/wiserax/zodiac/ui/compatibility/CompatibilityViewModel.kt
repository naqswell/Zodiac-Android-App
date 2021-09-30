package com.wiserax.zodiac.ui.compatibility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompatibilityViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Compatibility Fragment"
    }
    val text: LiveData<String> = _text
}