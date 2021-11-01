package com.wiserax.zodiac

import android.app.Application
import org.json.JSONObject

class Psychomatrix(application: Application, dateOfBirth: String) {
    private val arrayOneToNine: MutableMap<Int, Int> = calculateSquare(dateOfBirth)
    val textMap = getPsychomatrixText(application, arrayOneToNine)
    val matrixCellsData: Map<Int, String> = createSquare(arrayOneToNine)

    private fun calculateSquare(dateOfBirth: String): MutableMap<Int, Int> {
        val mapOfInt = mutableListOf<Int>()

        for (symbol in dateOfBirth) {
            if (symbol.isDigit()) {
                mapOfInt.add(symbol.digitToInt())
            }
        }

        val digitCounter = mutableMapOf(
//            0 to 0,
            1 to 0,
            2 to 0,
            3 to 0,
            4 to 0,
            5 to 0,
            6 to 0,
            7 to 0,
            8 to 0,
            9 to 0,
            123 to 0,
            456 to 0,
            789 to 0,
            147 to 0,
            258 to 0,
            369 to 0,
            159 to 0,
            357 to 0
        )

        for (el in mapOfInt) {
            if (el != 0) {
                digitCounter[el] = digitCounter[el]!! + 1
            }
        }

        val firstValue = mapOfInt.sum()
        var part1 = firstValue % 10
        var part2 = firstValue / 10
        if ((part1 != 0) && (part2 != 0)) {
            digitCounter[part1] = digitCounter[part1]!! + 1
            digitCounter[part2] = digitCounter[part2]!! + 1
        }
        val secondValue = part1 + part2
        part1 = secondValue % 10
        part2 = secondValue / 10
        if ((part1 != 0) && (part2 != 0)) {
            digitCounter[part1] = digitCounter[part1]!! + 1
            digitCounter[part2] = digitCounter[part2]!! + 1
        }
        val thirdValue = firstValue - mapOfInt[0] * 2
        part1 = thirdValue % 10
        part2 = thirdValue / 10
        if ((part1 != 0) && (part2 != 0)) {
            digitCounter[part1] = digitCounter[part1]!! + 1
            digitCounter[part2] = digitCounter[part2]!! + 1
        }
        val fourthValue = part1 + part2
        part1 = fourthValue % 10
        part2 = fourthValue / 10
        if ((part1 != 0) && (part2 != 0)) {
            digitCounter[part1] = digitCounter[part1]!! + 1
            digitCounter[part2] = digitCounter[part2]!! + 1
        }


        digitCounter[123] = digitCounter[1]!! + digitCounter[2]!! + digitCounter[3]!!
        digitCounter[456] = digitCounter[4]!! + digitCounter[5]!! + digitCounter[6]!!
        digitCounter[789] = digitCounter[7]!! + digitCounter[8]!! + digitCounter[9]!!
        digitCounter[147] = digitCounter[1]!! + digitCounter[4]!! + digitCounter[7]!!
        digitCounter[258] = digitCounter[2]!! + digitCounter[5]!! + digitCounter[8]!!
        digitCounter[369] = digitCounter[3]!! + digitCounter[6]!! + digitCounter[9]!!
        digitCounter[159] = digitCounter[1]!! + digitCounter[5]!! + digitCounter[9]!!
        digitCounter[357] = digitCounter[3]!! + digitCounter[5]!! + digitCounter[7]!!

        return digitCounter
    }

    private fun createSquare(arrayOneToNine: MutableMap<Int, Int>): Map<Int, String> {
        val a = arrayOneToNine.mapValues() {
            if (it.value == 0) {
                "---"
            } else {
                var str = ""
                for (el in 1..it.value) {
                    str += it.key
                }
                str
            }
        }
        return a
    }

    fun getPsychomatrixText(
        application: Application,
        digitCounter: MutableMap<Int, Int>
    ): MutableMap<Int, String> {

        val textMap = mutableMapOf(
            1 to "",
            2 to "",
            3 to "",
            4 to "",
            5 to "",
            6 to "",
            7 to "",
            8 to "",
            9 to "",
            123 to "",
            456 to "",
            789 to "",
            147 to "",
            258 to "",
            369 to "",
            159 to "",
            357 to ""
        )

        val file = application.assets.open("psychomatrix.json")
        val text = file.bufferedReader().use { it.readText() }

        val arrayNumbers = JSONObject(text).getJSONArray("entities")
        for (numbersIterator in 0 until arrayNumbers.length()) {
            val number = arrayNumbers.getJSONObject(numbersIterator)
            val arrayPower = number.getJSONArray("power")

            for (powerIterator in 0 until arrayPower.length()) {
                val power = arrayPower.getJSONObject(powerIterator)
                if (power.getInt("quantity") == digitCounter[number.getInt("number")]) {
                    textMap[number.getInt("number")] = power.getString("text")
                }
            }
        }
        return textMap
    }
}

