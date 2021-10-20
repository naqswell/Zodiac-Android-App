package com.wiserax.zodiac

import android.app.Application
import org.json.JSONArray
import org.json.JSONObject

class UserFactory {

    companion object {
        fun createUser(application: Application, gender: Gender, birthDate: String): User {
            val jsonObject = getJsonObject(application, birthDate)

            val name = jsonObject.getString("name")
            val date = jsonObject.getString("date")
            val generalText = jsonObject.getString("generalText")
            val additionText = jsonObject.getString("additionText")
            val loveText = jsonObject.getString("loveText")
            val workText = jsonObject.getString("workText")
            val healthText = jsonObject.getString("healthText")
            val luckText = jsonObject.getString("luckText")
            val adviceText = jsonObject.getString("adviceText")
            val sex = when(gender) {
                Gender.Male -> "maleText"
                Gender.Female -> "femaleText"
            }

            return User(
                name, date, generalText, jsonObject.getString(sex), additionText,
                loveText, workText, healthText, luckText, adviceText
            )
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


}