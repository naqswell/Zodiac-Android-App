package com.wiserax.zodiac.ui.horoscope

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wiserax.zodiac.Gender
import com.wiserax.zodiac.R
import com.wiserax.zodiac.UserFactory
import com.wiserax.zodiac.prefs

class HoroscopeViewModel(application: Application) : AndroidViewModel(application) {

    private val user =
        UserFactory.createUser(application, Gender.valueOf(prefs.sex), prefs.getDayAndMonth())

    val name: LiveData<String> = MutableLiveData<String>().apply {
        value = user.name
    }
    val image: LiveData<Drawable> = MutableLiveData<Drawable>().apply {
        value = user.getImage(application)
    }
    val generalText: LiveData<String> = MutableLiveData<String>().apply {
        value = user.getGeneralTextData()
    }
    val genderText: LiveData<String> = MutableLiveData<String>().apply {
        value = user.getGenderTextData()
    }
    val additionalText: LiveData<String> = MutableLiveData<String>().apply {
        value = user.getAdditionallTextData()
    }
    val date: LiveData<String> = MutableLiveData<String>().apply {
        value = prefs.day.toString() + " " + getApplication<Application>().resources.getStringArray(
            R.array.months)[prefs.month-1] + " " + prefs.year.toString()
    }

}