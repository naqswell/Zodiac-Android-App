package com.wiserax.zodiac

import android.app.Application
import com.wiserax.zodiac.model.Sign
import org.json.JSONArray
import org.json.JSONObject

class JsonReader(private val application: Application) {

    fun getCompatibilityTextBySignsPair(sign1: Sign, sign2: Sign): Map<String, String> {
        val file = application.assets.open("compability.json")
        val text = file.bufferedReader().use { it.readText() }
        val array = JSONArray(text)

        for (i in 0 until array.length()) {
            val jsonObject = JSONObject(array[i].toString())
            val jsonArray = jsonObject.getJSONArray("partner")

            if (jsonArray[0] == sign1.toString() && jsonArray[1] == sign2.toString()) {
                val titlesList = listOf(
                    "general", "work", "friendship", "love", "intimacy", "family",
                    "generalPower", "lovePower", "friendshipPower", "workPower", "familyPower"
                )

                val resultMap = mutableMapOf<String, String>()

                titlesList.forEach { title ->
                    val value = jsonObject.getString(title)

                    val key = when(title) {
                        "general" ->    application.resources.getString(R.string.general)
                        "work" ->       application.resources.getString(R.string.work)
                        "friendship" -> application.resources.getString(R.string.friendship)
                        "love" ->       application.resources.getString(R.string.love)
                        "intimacy" ->   application.resources.getString(R.string.intimacy)
                        "family" ->     application.resources.getString(R.string.family)
                        else -> title
                    }

                    resultMap[key] = value
                }

                return resultMap
            }
        }

        return mapOf()
    }
}