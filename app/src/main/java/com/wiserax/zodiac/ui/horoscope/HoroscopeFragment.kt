package com.wiserax.zodiac.ui.horoscope

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.wiserax.zodiac.R
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


        val nameTextView: TextView = binding.textName
        val imageView: ImageView = binding.imageView
        val generalTextView: TextView = binding.textViewHoroscopeGeneral
        val genderTextView: TextView = binding.textViewHoroscopeGender
        val additionalTextView: TextView = binding.textViewHoroscopeAddition
        val dateTextView: TextView = binding.textViewBirthdate
        val chooseDateBtn: MaterialButton = binding.chooseDateBtn
        val scrollView: ScrollView = binding.scrollViewHoroscope

        horoscopeViewModel.name.observe(viewLifecycleOwner, Observer {
            nameTextView.text = it
        })

        horoscopeViewModel.image.observe(viewLifecycleOwner, Observer {
            imageView.setImageDrawable(it)
        })

        horoscopeViewModel.generalText.observe(viewLifecycleOwner, Observer {
            generalTextView.text = it
        })

        horoscopeViewModel.genderText.observe(viewLifecycleOwner, Observer {
            genderTextView.text = it
        })

        horoscopeViewModel.additionalText.observe(viewLifecycleOwner, Observer {
            additionalTextView.text = it
        })

        horoscopeViewModel.date.observe(viewLifecycleOwner, Observer {
            dateTextView.text = it
        })


        if (resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES) {
            chooseDateBtn.setIconTintResource(R.color.white)
        } else {
            chooseDateBtn.setIconTintResource(R.color.black)
        }

        chooseDateBtn.setOnClickListener{
            findNavController().navigate(R.id.navigation_birthdate)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}