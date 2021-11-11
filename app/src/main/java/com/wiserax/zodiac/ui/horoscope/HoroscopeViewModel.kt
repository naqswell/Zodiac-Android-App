package com.wiserax.zodiac.ui.horoscope

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.wiserax.zodiac.Gender
import com.wiserax.zodiac.prefs

class HoroscopeViewModel(application: Application) : AndroidViewModel(application) {
    val user = if (prefs.isDateInit()) {
        HoroscopeModel.createUser(application, Gender.valueOf(prefs.sex), prefs.getDayAndMonth())
    } else {
        HoroscopeModel.createUser(application, Gender.Male, "01.01")
    }
}