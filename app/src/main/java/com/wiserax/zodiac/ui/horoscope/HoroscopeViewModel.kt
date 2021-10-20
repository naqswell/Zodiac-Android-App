package com.wiserax.zodiac.ui.horoscope

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wiserax.zodiac.Gender
import com.wiserax.zodiac.R
import com.wiserax.zodiac.User
import com.wiserax.zodiac.prefs

class HoroscopeViewModel(application: Application) : AndroidViewModel(application) {

    private val user: User = User(application, prefs.getDayAndMonth())

    val name: LiveData<String> = MutableLiveData<String>().apply {
        value = user.getText(User.Static.JsonFields.Name)
    }
    val image: LiveData<Drawable> = MutableLiveData<Drawable>().apply {
        value = User.getImage(User.Static.JsonFields.Name, application)
    }
    val generalText: LiveData<String> = MutableLiveData<String>().apply {
        value = user.getText(User.Static.JsonFields.GeneralText)
    }
    val genderText: LiveData<String> =  MutableLiveData<String>().apply {
        value = user.getText(when(Gender.valueOf(prefs.sex)) {
            Gender.Male -> User.Static.JsonFields.MaleText
            Gender.Female -> User.Static.JsonFields.FemaleText
        })
    }
    val additionalText: LiveData<String> = MutableLiveData<String>().apply {
        value = user.getText(User.Static.JsonFields.AdditionText)
    }
    val date: LiveData<String> = MutableLiveData<String>().apply {
        value = dateToString()
    }

    private fun dateToString() : String {
         return prefs.day.toString() + " " + getApplication<Application>().resources.getStringArray(R.array.months)[prefs.month-1] + " " + prefs.year.toString()
    }
}