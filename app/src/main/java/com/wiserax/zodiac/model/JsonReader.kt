package com.wiserax.zodiac.model

import android.app.Application
import com.wiserax.zodiac.ui.compatibility.Sign
import org.json.JSONArray
import org.json.JSONObject

class JsonReader(private val application: Application) {

    fun getHoroscopeObject(birthDate: String): JSONObject {
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

    fun getCompatibilityObject(sign1: Sign, sign2: Sign): JSONObject {
        val file = application.assets.open("compatibility.json")
        val text = file.bufferedReader().use { it.readText() }
        val array = JSONArray(text)

        for (i in 0 until array.length()) {
            val jsonObject = JSONObject(array[i].toString())
            val jsonArray = jsonObject.getJSONArray("partner")

            if (
                jsonArray[0] == sign1.toString() && jsonArray[1] == sign2.toString() ||
                jsonArray[1] == sign1.toString() && jsonArray[0] == sign2.toString()
            ) {
                return jsonObject
            }
        }

        return JSONObject(array[0].toString())
    }
}