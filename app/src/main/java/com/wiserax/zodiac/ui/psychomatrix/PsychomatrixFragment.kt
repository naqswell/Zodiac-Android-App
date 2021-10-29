package com.wiserax.zodiac.ui.psychomatrix

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.MatrixFactory
import com.wiserax.zodiac.databinding.FragmentPsychomatrixBinding
import com.wiserax.zodiac.prefs

class PsychomatrixFragment : Fragment() {

    private lateinit var psychomatrixViewModel: PsychomatrixViewModel
    private var _binding: FragmentPsychomatrixBinding? = null
    private lateinit var matrix: Array<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("fragment", "Psycho onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        psychomatrixViewModel =
            ViewModelProvider(this).get(PsychomatrixViewModel::class.java)

        _binding = FragmentPsychomatrixBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        matrix = MatrixFactory.calculateSquare(prefs.getFullDate()!!)

        with(binding) {
//            textView1Temper.text = matrix[1]
//            textView2Charisma.text = matrix[2]
//            textView3Knowledge.text = matrix[3]
//            textView4Health.text = matrix[4]
//            textView5Logic.text = matrix[5]
//            textView6Mastery.text = matrix[6]
//            textView7Luck.text = matrix[7]
//            textView8SenseOfDuty.text = matrix[8]
//            textView9Intelligence.text = matrix[9]
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}