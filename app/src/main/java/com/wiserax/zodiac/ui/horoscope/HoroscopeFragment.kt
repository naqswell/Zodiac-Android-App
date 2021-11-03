package com.wiserax.zodiac.ui.horoscope

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentHoroscopeBinding

class HoroscopeFragment : Fragment() {

    private lateinit var horoscopeViewModel: HoroscopeViewModel

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        horoscopeViewModel =
            ViewModelProvider(this)[HoroscopeViewModel::class.java]

        _binding = FragmentHoroscopeBinding.inflate(inflater, container, false)

        initializeObservables()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeObservables() {

        with(horoscopeViewModel) {
            name.observe(viewLifecycleOwner, {
                binding.textName.text = it
            })

            image.observe(viewLifecycleOwner, {
                binding.imageView.setImageDrawable(it)
            })

            generalText.observe(viewLifecycleOwner, {
                binding.textViewHoroscopeGeneral.text = it
            })

            genderText.observe(viewLifecycleOwner, {
                binding.textViewHoroscopeGender.text = it
            })

            additionalText.observe(viewLifecycleOwner, {
                binding.textViewHoroscopeAddition.text = it
            })

            loveText.observe(viewLifecycleOwner, {
                binding.textViewHoroscopeLove.text = it
            })

            workText.observe(viewLifecycleOwner, {
                binding.textViewHoroscopeWork.text = it
            })

            healthText.observe(viewLifecycleOwner, {
                binding.textViewHoroscopeHealth.text = it
            })

            luckText.observe(viewLifecycleOwner, {
                binding.textViewHoroscopeLuck.text = it
            })

            adviceText.observe(viewLifecycleOwner, {
                binding.textViewHoroscopeAdvice.text = it
            })

        }
    }
}