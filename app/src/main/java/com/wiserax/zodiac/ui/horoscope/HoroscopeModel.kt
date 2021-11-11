package com.wiserax.zodiac.ui.horoscope

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.wiserax.zodiac.Gender
import com.wiserax.zodiac.R
import org.json.JSONArray
import org.json.JSONObject

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
            val jsonObject = getJsonObject(application, birthDate)
            val textMap: MutableMap<String, String> = mutableMapOf()
            var name = ""

            for (it in jsonObject.keys()) {
                when (it) {
                    "name" -> name = jsonObject.getString(it)
                    "date" -> {}
                    "maleText" -> if (gender == Gender.Male) {
                        textMap["genderText"] = jsonObject.getString(it)
                    }
                    "femaleText" -> if (gender == Gender.Female) {
                        textMap["genderText"] = jsonObject.getString(it)
                    }
                    else -> textMap[it] = jsonObject.getString(it)
                }
            }
            return User(name, textMap, getImage(name, application))
        }

        private fun getJsonObject(application: Application, birthDate: String): JSONObject {
            val file = application.assets.open("character.json")
            val text = file.bufferedReader().use { it.readText() }
            val array = JSONArray(text)

            for (i in 0 until array.length()) {
                val jsonObject = JSONObject(array[i].toString())
                if (jsonObject.getString("date") == birthDate) {
                    return jsonObject
                }
            }
            return JSONObject(array[0].toString())
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