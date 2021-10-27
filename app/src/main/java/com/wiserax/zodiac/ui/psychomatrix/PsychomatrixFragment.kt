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

    private lateinit var psychomatrixViewModel: PsychomatrixViewModel
    private var _binding: FragmentPsychomatrixBinding? = null

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}