package com.wiserax.zodiac.ui.psychomatrix

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.databinding.FragmentPsychomatrixBinding

class PsychomatrixFragment : Fragment() {

    private lateinit var viewModel: PsychomatrixViewModel
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
        viewModel =
            ViewModelProvider(this)[PsychomatrixViewModel::class.java]

        _binding = FragmentPsychomatrixBinding.inflate(inflater, container, false)
        val root: View = binding.root


        with(binding) {
            textView1Temper.text = viewModel.matrixCells.value?.get(1)
            textView2Charisma.text = viewModel.matrixCells.value?.get(2)
            textView3Knowledge.text = viewModel.matrixCells.value?.get(3)
            textView4Health.text = viewModel.matrixCells.value?.get(4)
            textView5Logic.text = viewModel.matrixCells.value?.get(5)
            textView6Mastery.text = viewModel.matrixCells.value?.get(6)
            textView7Luck.text = viewModel.matrixCells.value?.get(7)
            textView8SenseOfDuty.text = viewModel.matrixCells.value?.get(8)
            textView9Intelligence.text = viewModel.matrixCells.value?.get(9)


            textViewNum1.text = viewModel.matrixText.value?.get(1)
            textViewNum2.text = viewModel.matrixText.value?.get(2)
            textViewNum3.text = viewModel.matrixText.value?.get(3)
            textViewNum4.text = viewModel.matrixText.value?.get(4)
            textViewNum5.text = viewModel.matrixText.value?.get(5)
            textViewNum6.text = viewModel.matrixText.value?.get(6)
            textViewNum7.text = viewModel.matrixText.value?.get(7)
            textViewNum8.text = viewModel.matrixText.value?.get(8)
            textViewNum9.text = viewModel.matrixText.value?.get(9)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}