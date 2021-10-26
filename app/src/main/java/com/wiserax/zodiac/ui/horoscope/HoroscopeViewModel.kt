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

    private val user = if (prefs.getDateInitFlag()) {
        UserFactory.createUser(application, Gender.valueOf(prefs.sex), prefs.getDayAndMonth())
    } else {
        UserFactory.createUser(application, Gender.Male, "01.01")
    }

    val name: LiveData<String> = MutableLiveData<String>().apply { value = user.name }

    val image: LiveData<Drawable> =
        MutableLiveData<Drawable>().apply { value = user.getImage(application) }

    val generalText: LiveData<String> = MutableLiveData<String>().apply { value = user.generalText }

    val genderText: LiveData<String> = MutableLiveData<String>().apply { value = user.genderText }

    val additionalText: LiveData<String> =
        MutableLiveData<String>().apply { value = user.additionText }

    val loveText: LiveData<String> = MutableLiveData<String>().apply { value = user.loveText }

    val workText: LiveData<String> = MutableLiveData<String>().apply { value = user.workText }

    val healthText: LiveData<String> = MutableLiveData<String>().apply { value = user.healthText }

    val luckText: LiveData<String> = MutableLiveData<String>().apply { value = user.luckText }

    val adviceText: LiveData<String> = MutableLiveData<String>().apply { value = user.adviceText }

    val date: LiveData<String> = MutableLiveData<String>().apply {
        value = if (prefs.getDateInitFlag()) {
            prefs.day.toString() + " " + getApplication<Application>().resources.getStringArray(
                R.array.months
            )[prefs.month - 1] + " " + prefs.year.toString()
        } else {
            ""
        }
    }
}