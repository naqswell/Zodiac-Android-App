package com.wiserax.zodiac.ui.birthdate

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentDateBinding
import androidx.appcompat.app.AppCompatDelegate
import com.wiserax.zodiac.prefs


class DateFragment : Fragment() {

    interface Callbacks {
        fun onDateButtonPressed()
    }

    private val viewModel: DateViewModel by activityViewModels()
    private var _binding: FragmentDateBinding? = null
    private val binding get() = _binding!!
    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

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

            viewModel.date.observe(viewLifecycleOwner) {
                textViewBirthdate.text = it
            }

            chooseDateBtn.setOnClickListener {
                callbacks?.onDateButtonPressed()
            }


            if (prefs.isNightMode) {
                themeSwitch.isChecked = true
            }

            themeSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    prefs.isNightMode = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    prefs.isNightMode = false
                }
            }
        }

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
}