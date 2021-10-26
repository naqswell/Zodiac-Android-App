package com.wiserax.zodiac.ui.horoscope

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
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
    ): View? {

        horoscopeViewModel =
            ViewModelProvider(this).get(HoroscopeViewModel::class.java)

        _binding = FragmentHoroscopeBinding.inflate(inflater, container, false)

        initializeObservables()

        val chooseDateBtn: MaterialButton = binding.chooseDateBtn

        if (resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES) {
            chooseDateBtn.setIconTintResource(R.color.white)
        } else {
            chooseDateBtn.setIconTintResource(R.color.black)
        }

        chooseDateBtn.setOnClickListener{
            findNavController().navigate(R.id.navigation_birthdate)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeObservables() {

        horoscopeViewModel.name.observe(viewLifecycleOwner, {
            binding.textName.text = it
        })

        horoscopeViewModel.image.observe(viewLifecycleOwner, {
            binding.imageView.setImageDrawable(it)
        })

        horoscopeViewModel.generalText.observe(viewLifecycleOwner, {
            binding.textViewHoroscopeGeneral.text = it
        })

        horoscopeViewModel.genderText.observe(viewLifecycleOwner, {
            binding.textViewHoroscopeGender.text = it
        })

        horoscopeViewModel.additionalText.observe(viewLifecycleOwner, {
            binding.textViewHoroscopeAddition.text = it
        })

        horoscopeViewModel.loveText.observe(viewLifecycleOwner, {
            binding.textViewHoroscopeLove.text = it
        })

        horoscopeViewModel.workText.observe(viewLifecycleOwner, {
            binding.textViewHoroscopeWork.text = it
        })

        horoscopeViewModel.healthText.observe(viewLifecycleOwner, {
            binding.textViewHoroscopeHealth.text = it
        })

        horoscopeViewModel.luckText.observe(viewLifecycleOwner, {
            binding.textViewHoroscopeLuck.text = it
        })

        horoscopeViewModel.adviceText.observe(viewLifecycleOwner, {
            binding.textViewHoroscopeAdvice.text = it
        })

        horoscopeViewModel.date.observe(viewLifecycleOwner, {
            binding.textViewBirthdate.text = it
        })
    }
}