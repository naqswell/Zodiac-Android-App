package com.wiserax.zodiac.ui.psychomatrix

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wiserax.zodiac.prefs

class PsychomatrixViewModel(application: Application) : AndroidViewModel(application) {
    private val psyMatrix = if (prefs.isDateInit()) {
        PsychomatrixModel(application, prefs.getFullDate()!!)
    } else PsychomatrixModel(application, "01.01.2001")

    val matrixCells: MutableMap<Int, String> = psyMatrix.matrixCellsData

    val matrixText: Map<Int, String> = psyMatrix.textMap
}