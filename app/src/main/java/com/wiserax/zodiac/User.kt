package com.wiserax.zodiac

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import java.lang.StringBuilder

data class User(
    val name: String,
    val date: String,
    val generalText: String,
    val genderText: String,
    val additionText: String,
    val loveText: String,
    val workText: String,
    val healthText: String,
    val luckText: String,
    val adviceText: String
) {

    fun getImage(application: Application): Drawable {
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
