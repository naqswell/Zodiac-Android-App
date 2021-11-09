package com.wiserax.zodiac.ui.birthdate

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.wiserax.zodiac.Gender
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentBirthdateBinding
import com.wiserax.zodiac.prefs
import java.util.*

class BirthDateFragment : Fragment() {

    interface Callbacks {
        fun onDateSet()
    }

    private val viewModel: DateViewModel by activityViewModels()
    private var _binding: FragmentBirthdateBinding? = null
    private val binding get() = _binding!!
    private var callbacks: Callbacks? = null

    private val calendar: Calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBirthdateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.date.observe(viewLifecycleOwner, {
            it?.let {
                binding.chooseDateTextview.text =
                    String.format(resources.getString(R.string.your_birthdate), it)
            }
        })

        when (prefs.sex) {
            Gender.Male.text -> binding.radioGroup.check(R.id.male)
            Gender.Female.text -> binding.radioGroup.check(R.id.female)
        }

        binding.btnChooseDate.setOnClickListener {
            showDatePickerDialog()
        }


        binding.btnGetHoroscope.setOnClickListener {
            val selectedId = binding.radioGroup.checkedRadioButtonId
            if ((selectedId != -1) && (viewModel.date.value != null)) {
                val sex: String = if (binding.male.isChecked) {
                    binding.male.text.toString()
                } else {
                    binding.female.text.toString()
                }
                when (sex) {
                    binding.male.text.toString() -> prefs.sex = Gender.Male.text
                    binding.female.text.toString() -> prefs.sex = Gender.Female.text
                }
                callbacks?.onDateSet()
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
            viewModel.date.value = prefs.getDateFormated()
        }, year, month, day)
        dpd.show()
        dpd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.orange, requireActivity().theme))
        dpd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.orange, requireActivity().theme))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
}