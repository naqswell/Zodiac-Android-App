package com.wiserax.zodiac.ui.horoscope

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.wiserax.zodiac.model.Gender
import com.wiserax.zodiac.R
import com.wiserax.zodiac.model.JsonReader

class HoroscopeModel {

    data class User(
        val name: String,
        val textMap: MutableMap<String, String>,
        val image: Drawable
    )

    companion object {
        fun createUser(
            application: Application,
            gender: Gender,
            birthDate: String
        ): User {
            val jsonReader = JsonReader(application)
            val jsonObject = jsonReader.getHoroscopeObject(birthDate)

            val textMap: MutableMap<String, String> = mutableMapOf()
            var name = ""

            for (it in jsonObject.keys()) {
                when (it) {
                    "name" -> name = jsonObject.getString(it)
                    "date" -> {}
                    "maleText" -> if (gender == Gender.Male) {
                        Log.d("GENDER", "MEN " + textMap["genderText"].toString())
                    }
                    "femaleText" -> if (gender == Gender.Female) {
                        textMap["genderText"] = jsonObject.getString(it)
                    }
                    else -> textMap[it] = jsonObject.getString(it)
                }
            }
            return User(name, textMap, getImage(name, application))
        }

        private fun getImage(name: String, application: Application): Drawable {
            return when (name) {
                "Козерог" -> getImage(application, R.drawable.ic_sign_capricorn)
                "Водолей" -> getImage(application, R.drawable.ic_sign_aquaris)
                "Рыбы" -> getImage(application, R.drawable.ic_sign_pisces)
                "Овен" -> getImage(application, R.drawable.ic_sign_aries)
                "Телец" -> getImage(application, R.drawable.ic_sign_taurus)
                "Близнецы" -> getImage(application, R.drawable.ic_sign_gemini)
                "Рак" -> getImage(application, R.drawable.ic_sign_cancer)
                "Лев" -> getImage(application, R.drawable.ic_sign_leo)
                "Дева" -> getImage(application, R.drawable.ic_sign_virgo)
                "Весы" -> getImage(application, R.drawable.ic_sign_libra)
                "Скорпион" -> getImage(application, R.drawable.ic_sign_scorpio)
                "Стрелец" -> getImage(application, R.drawable.ic_sign_sagittarius)
                else -> getImage(application, R.drawable.ic_sign_empty)
            }
        }

        private fun getImage(application: Application, path: Int): Drawable {
            return ResourcesCompat.getDrawable(application.resources, path, null)!!
        }
    }
}