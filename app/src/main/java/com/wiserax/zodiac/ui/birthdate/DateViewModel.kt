package com.wiserax.zodiac.ui.birthdate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.wiserax.zodiac.prefs

class DateViewModel(application: Application) : AndroidViewModel(application) {
    val date = MutableLiveData<String?>().apply {
        value = if (prefs.isDateInit()) {
            prefs.getDateFormated()
        } else {
            null
//            application.resources.getString(R.string.choose_your_birthdate_alert)
        }
    }
}