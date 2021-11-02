package com.wiserax.zodiac.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentDateBinding

class DateFragment: Fragment() {

    private var _binding: FragmentDateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateBinding.inflate(inflater, container, false)

        with(binding) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (resources.configuration.isNightModeActive) {
                    chooseDateBtn.setIconTintResource(R.color.grey_ic_nav_bar)
                } else {
                    chooseDateBtn.setIconTintResource(R.color.black)
                }
            }

            chooseDateBtn.setOnClickListener {
                findNavController().navigate(R.id.navigation_birthdate)
            }
        }

        return binding.root
    }
}