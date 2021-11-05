package com.wiserax.zodiac.ui.psychomatrix

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wiserax.zodiac.prefs

class PsychomatrixViewModel(application: Application) : AndroidViewModel(application) {
    private val psyMatrix = if (prefs.isDateInit()) {
        Psychomatrix(application, prefs.getFullDate()!!)
    } else Psychomatrix(application, "01.01.2001")

    val matrixCells: LiveData<Map<Int, String>> = MutableLiveData(psyMatrix.matrixCellsData)

    val matrixText: LiveData<Map<Int, String>> = MutableLiveData(psyMatrix.textMap)
}