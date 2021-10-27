package com.wiserax.zodiac.ui.psychomatrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.databinding.FragmentPsychomatrixBinding

class PsychomatrixFragment : Fragment() {

    private lateinit var psychomatrixViewModel: PsychomatrixViewModel

    private var _binding: FragmentPsychomatrixBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        psychomatrixViewModel =
            ViewModelProvider(this).get(PsychomatrixViewModel::class.java)

        _binding = FragmentPsychomatrixBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}