package com.wiserax.zodiac.ui.birthdate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.wiserax.zodiac.R
import com.wiserax.zodiac.prefs

class DateViewModel(application: Application) : AndroidViewModel(application) {
    val date = MutableLiveData<String>().apply {
        value = prefs.day.toString() + " " + application.resources.getStringArray(
            R.array.months
        )[prefs.month - 1] + " " + prefs.year.toString()
    }
}