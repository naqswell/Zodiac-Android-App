package com.wiserax.zodiac

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val PREF_BIRTHDATE = "prefBirthDate"
    private val PREF_DATE = "prefDay"
    private val PREF_MONTH = "prefMonth"
    private val PREF_YEAR = "prefYear"
    private val PREF_SEX = "prefSex"
    val DEF_INT_VALUE = -1
    val DEF_STRING_VALUE = Gender.Male.text

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_BIRTHDATE, Context.MODE_PRIVATE)

    var day: Int
        get() = preferences.getInt(PREF_DATE, DEF_INT_VALUE)
        set(value) = preferences.edit().putInt(PREF_DATE, value).apply()

    var month: Int
        get() = preferences.getInt(PREF_MONTH, DEF_INT_VALUE)
        set(value) = preferences.edit().putInt(PREF_MONTH, value).apply()

    var year: Int
        get() = preferences.getInt(PREF_YEAR, DEF_INT_VALUE)
        set(value) = preferences.edit().putInt(PREF_YEAR, value).apply()

    var sex: String
        get() = preferences.getString(PREF_SEX, DEF_STRING_VALUE)!!
        set(value) = preferences.edit().putString(PREF_SEX, value).apply()

    fun getDateInitFlag(): Boolean {
        return ((day != DEF_INT_VALUE) && (month != DEF_INT_VALUE) && (year != DEF_INT_VALUE))
    }

    fun getFullDate(): String? {
        return if (getDateInitFlag()) {
            String.format("%02d/%02d/%04d", day, month, year)
        } else {
            null
        }
    }

    fun getDayAndMonth(): String {
        return String.format("%02d.%02d", day, month)
    }
}