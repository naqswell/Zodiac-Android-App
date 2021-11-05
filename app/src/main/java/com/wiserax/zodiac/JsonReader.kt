package com.wiserax.zodiac

import android.app.Application
import com.wiserax.zodiac.model.Sign
import org.json.JSONArray
import org.json.JSONObject

class JsonReader(private val application: Application) {

    fun getCompatibility(sign1: Sign, sign2: Sign): Pair<Map<String, String>, Map<String, String>> {
        val file = application.assets.open("compatibility.json")
        val text = file.bufferedReader().use { it.readText() }
        val array = JSONArray(text)

        for (i in 0 until array.length()) {
            val jsonObject = JSONObject(array[i].toString())
            val jsonArray = jsonObject.getJSONArray("partner")

            if (jsonArray[0] == sign1.toString() && jsonArray[1] == sign2.toString()) {
                val textMap = getCompatibilityText(jsonObject)
                val percentagesMap = getCompatibilityPercentages(jsonObject)

                return Pair(textMap, percentagesMap)
            }
        }

        return Pair(mapOf(), mapOf())
    }

    private fun getCompatibilityText(jsonObject: JSONObject): Map<String, String> {
        val resultsMap = mutableMapOf<String, String>()
        val titlesMap = mapOf(
            "general" to application.resources.getString(R.string.general),
            "work" to application.resources.getString(R.string.work),
            "friendship" to application.resources.getString(R.string.friendship),
            "love" to application.resources.getString(R.string.love),
            "intimacy" to application.resources.getString(R.string.intimacy),
            "family" to application.resources.getString(R.string.family),
        )

        titlesMap.forEach { (name, title) ->
            resultsMap[title] = jsonObject.getString(name)
        }

        return resultsMap
    }

    private fun getCompatibilityPercentages(jsonObject: JSONObject): Map<String, String> {
        val resultsMap = mutableMapOf<String, String>()
        val percentagesList = listOf(
            "generalPower",
            "lovePower",
            "friendshipPower",
            "workPower",
            "familyPower"
        )

        percentagesList.forEach { percentage ->
            resultsMap[percentage] = jsonObject.getString(percentage)
        }

        return resultsMap
    }
}