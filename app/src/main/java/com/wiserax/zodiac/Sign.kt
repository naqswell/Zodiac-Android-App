package com.wiserax.zodiac

import com.wiserax.zodiac.R

enum class Sign(val title: String, val dates: String, val image: Int) {
    Aquarius("Водолей", "20 янв. - 19 фев.", R.drawable.ic_sign_aquaris),
    Pisces("Рыбы", "20 фев. - 21 мар.", R.drawable.ic_sign_pisces),
    Aries("Овен", "22 мар. - 21 апр.", R.drawable.ic_sign_aries),
    Taurus("Телец","22 апр. - 21 мая", R.drawable.ic_sign_taurus),
    Gemini("Близнецы", "22 мая - 21 июн.", R.drawable.ic_sign_gemini),
    Cancer("Рак", "22 июн. - 21 июл.", R.drawable.ic_sign_cancer),
    Leo("Лев", "22 июл. - 21 авг.", R.drawable.ic_sign_leo),
    Virgo("Дева", "22 авг. - 21 сен.", R.drawable.ic_sign_virgo),
    Libra("Весы", "22 сен. - 21 окт.", R.drawable.ic_sign_libra),
    Scorpio("Скорпион", "22 окт. - 20 нояб.", R.drawable.ic_sign_scorpio),
    Sagittarius("Стрелец", "21 нояб. - 21 дек.", R.drawable.ic_sign_sagittarius),
    Capricorn("Козерог", "22 дек. - 19 янв.", R.drawable.ic_sign_capricorn)
}