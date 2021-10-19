package com.wiserax.zodiac.ui.horoscope

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wiserax.zodiac.Gender
import com.wiserax.zodiac.UserFactory
import com.wiserax.zodiac.prefs

class HoroscopeViewModel(application: Application) : AndroidViewModel(application) {

    private val user =
        UserFactory.createUser(application, Gender.valueOf(prefs.sex), prefs.getDayAndMonth())

    private val _name = MutableLiveData<String>().apply {
        value = user.name
    }

    private val _image = MutableLiveData<Drawable>().apply {
        value = user.getImage(application)
    }

    private val _description = MutableLiveData<String>().apply {
        value = user.getDescription()
    }

    val name: LiveData<String> = _name
    val image: LiveData<Drawable> = _image
    val description: LiveData<String> = _description
}