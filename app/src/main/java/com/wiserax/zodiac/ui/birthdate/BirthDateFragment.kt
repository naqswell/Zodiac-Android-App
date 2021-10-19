package com.wiserax.zodiac.ui.birthdate

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wiserax.zodiac.Gender
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentBirthdateBinding
import com.wiserax.zodiac.prefs
import java.util.*

class BirthDateFragment : Fragment() {

    private var _binding: FragmentBirthdateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var dateString: String? = prefs.getFullDate()

    private val calendar: Calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBirthdateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dateString?.let {
            binding.chooseDateTextview.text = String.format(
                resources.getString(R.string.your_birthdate), it
            )
        }

        binding.btnChooseDate.setOnClickListener {
            showDatePickerDialog()
        }


        binding.btnGetHoroscope.setOnClickListener {
            val selectedId = binding.radioGroup.checkedRadioButtonId
            if ((selectedId != -1) && (dateString != null)) {
                findNavController().navigate(R.id.navigation_horoscope)
                val sex: String = if (binding.male.isChecked) {
                    binding.male.text.toString()
                } else {
                    binding.female.text.toString()
                }
                when (sex) {
                    binding.male.text.toString() -> prefs.sex = Gender.Male.text
                    binding.female.text.toString() -> prefs.sex = Gender.Female.text
                }
                Log.d("GGG", sex)
            } else {
                binding.chooseDateTextview.text =
                    resources.getString(R.string.choose_your_birthdate_alert)
            }
        }
        return root
    }

    private fun showDatePickerDialog() {
        val dpd = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->

            prefs.day = dayOfMonth
            prefs.month =
                monthOfYear + 1 // Да именно так, спасибо Android SDK за отчёт с 0, а не с 1
            prefs.year = year

            dateString = prefs.getFullDate()
            binding.chooseDateTextview.text = String.format(
                resources.getString(R.string.your_birthdate), dateString
            )
        }, year, month, day)
        dpd.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}