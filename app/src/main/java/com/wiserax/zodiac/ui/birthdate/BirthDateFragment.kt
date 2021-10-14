package com.wiserax.zodiac.ui.birthdate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentBirthdateBinding

class BirthDateFragment : Fragment(){

    private var _binding: FragmentBirthdateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBirthdateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spinner: Spinner = binding.spinnerMonth
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.months,
            R.layout.birthdate_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.birthdate_spinner_item_dropdown)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        val getHoroscopeBtn: Button = binding.getHoroscopeBtn

        getHoroscopeBtn.setOnClickListener {
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}