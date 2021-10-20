package com.wiserax.zodiac

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder

class User(application: Application, private val birthDate: String) {
    private val jsonObject: JSONObject = getJsonObject(application, prefs.getDayAndMonth())

    companion object Static{
        enum class JsonFields(val text: String) {
            Name("name"),
            Date("date"),
            GeneralText("generalText"),
            MaleText("maleText"),
            FemaleText("femaleText"),
            AdditionText("additionText"),
            LoveText("loveText"),
            WorkText("workText"),
            HealthText("healthText"),
            LuckText("luckText"),
            AdviceText("adviceText")
        }

        fun getImage(type: JsonFields, application: Application): Drawable {
            return when (type.text) {
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

        private fun getJsonObject(application: Application, birthDate: String): JSONObject {
            val file = application.assets.open("character_1.json")
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
    }

    fun getText(type: JsonFields): String {
        return StringBuilder()
            .append("${jsonObject.getString(type.text)}\n\n")
            .toString()
    }

}




