package com.wiserax.zodiac

import android.app.Application
import org.json.JSONArray
import org.json.JSONObject

class PsychoMatrixFactory {

    data class PsychoMatrixTextFields(
        val number1: String,
        val number2: String,
        val number3: String,
        val number4: String,
        val number5: String,
        val number6: String,
        val number7: String,
        val number8: String,
        val number9: String,
        val number123: String,
        val number456: String,
        val number789: String,
        val number147: String,
        val number258: String,
        val number369: String,
        val number159: String,
        val number357: String,
    )

    private fun calculateSquare(dateOfBirth: String): Array<String> {
        val arrayOfInt = arrayListOf<Int>()

        for (symbol in dateOfBirth) {
            if (symbol.isDigit()) {
                arrayOfInt.add(symbol.digitToInt())
            }
        }

        val digitCounter = arrayListOf<Int>()
        for (el in 1..10) {
            digitCounter.add(0)
        }

        for (el in arrayOfInt) {
            digitCounter[el] += 1
        }

        val firstValue = arrayOfInt.sum()
        var part1 = firstValue % 10
        var part2 = firstValue / 10
        digitCounter[part1] += 1
        digitCounter[part2] += 1

        val secondValue = part1 + part2
        part1 = secondValue % 10
        part2 = secondValue / 10
        digitCounter[part1] += 1
        digitCounter[part2] += 1

        val thirdValue = firstValue - arrayOfInt[1] * 2
        part1 = thirdValue % 10
        part2 = thirdValue / 10
        digitCounter[part1] += 1
        digitCounter[part2] += 1

        val fourthValue = part1 + part2
        part1 = fourthValue % 10
        part2 = fourthValue / 10
        digitCounter[part1] += 1
        digitCounter[part2] += 1
//        val str =
//            firstValue.toString() + secondValue.toString() + thirdValue.toString() + fourthValue.toString()
//        val chech = 0 % 10
//        Log.d("SSS", chech.toString())
//        Log.d("SSS", arrayOfInt.toString() + "\n" + str)
        return createSquare(digitCounter)
    }

    private fun createSquare(intArray: ArrayList<Int>): Array<String> {
        return intArray.mapIndexed() {idx, value ->
            if (value == 0) {
                "---"
            } else {
                var str = ""
                for (el in 1..value) {
                    str += idx
                }
                str
            }
        }.toTypedArray()
    }

    companion object {
        fun createMatrix(application: Application, dateOfBirth: String) {
            val jsonObject = getJsonObject(application, dateOfBirth)

            val name = jsonObject.getString("name")
        }

        private fun getJsonObject(application: Application, birthDate: String): JSONObject {
            val file = application.assets.open("psychomatrix.json")
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