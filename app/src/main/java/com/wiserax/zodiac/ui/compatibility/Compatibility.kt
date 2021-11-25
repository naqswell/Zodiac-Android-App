package com.wiserax.zodiac.ui.compatibility

import android.app.Application
import com.wiserax.zodiac.R
import com.wiserax.zodiac.model.JsonReader

class Compatibility(private val application: Application, sign1: Sign, sign2: Sign) {

    private val jsonReader = JsonReader(application)
    private val compatibilityObject = jsonReader.getCompatibilityObject(sign1, sign2)

    val generalPercentage: Int by lazy { initializeGeneralPercentage() }

    val percentages: Map<String, Int> by lazy { initializeCharacteristics() }
    val articles: Map<String, String> by lazy { initializeArticles() }

    private fun initializeGeneralPercentage(): Int {
        return compatibilityObject.getString("generalPower").toInt()
    }

    private fun initializeCharacteristics(): Map<String, Int> {
        val resultMap = mutableMapOf<String, Int>()
        val characteristics = listOf(
            "lovePower",
            "friendshipPower",
            "workPower",
            "familyPower"
        )

        characteristics.forEach { characteristic ->
            resultMap[characteristic] = compatibilityObject.getString(characteristic).toInt()
        }

        return resultMap
    }

    private fun initializeArticles(): Map<String, String> {
        val resultMap = mutableMapOf<String, String>()
        val articles = mapOf(
            "general" to application.resources.getString(R.string.general),
            "work" to application.resources.getString(R.string.work),
            "friendship" to application.resources.getString(R.string.friendship),
            "love" to application.resources.getString(R.string.love),
            "intimacy" to application.resources.getString(R.string.intimacy),
            "family" to application.resources.getString(R.string.family),
        )

        articles.forEach { (name, article) ->
            resultMap[article] = compatibilityObject.getString(name)
        }

        return resultMap
    }
}