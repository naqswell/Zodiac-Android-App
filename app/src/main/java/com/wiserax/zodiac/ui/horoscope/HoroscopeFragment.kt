package com.wiserax.zodiac.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.databinding.FragmentHoroscopeBinding

class HoroscopeFragment : Fragment() {

    private lateinit var horoscopeViewModel: HoroscopeViewModel
    private var _binding: FragmentHoroscopeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        horoscopeViewModel =
            ViewModelProvider(this).get(HoroscopeViewModel::class.java)

        _binding = FragmentHoroscopeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHorosope
        horoscopeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}