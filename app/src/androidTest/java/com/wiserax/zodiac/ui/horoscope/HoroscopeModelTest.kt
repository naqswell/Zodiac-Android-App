package com.wiserax.zodiac.ui.horoscope

import com.wiserax.zodiac.App
import com.wiserax.zodiac.model.Gender
import org.junit.Test

class HoroscopeModelTest {
    @Test
    fun testLol() {
        val horoscopeModel = HoroscopeModel.createUser(App.instance, Gender.Male, "01.01")
        assert(horoscopeModel.textMap.size == 8)
    }
}