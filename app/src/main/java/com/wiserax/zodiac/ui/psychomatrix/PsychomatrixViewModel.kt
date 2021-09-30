package com.wiserax.zodiac.ui.psychomatrix

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PsychomatrixViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Psychomatrix Fragment"
    }
    val text: LiveData<String> = _text
}