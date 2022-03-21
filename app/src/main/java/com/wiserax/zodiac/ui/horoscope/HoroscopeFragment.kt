package com.wiserax.zodiac.ui.horoscope

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentHoroscopeBinding
import com.wiserax.zodiac.ui.birthdate.DateViewModel

class HoroscopeFragment : Fragment() {

    private lateinit var horoscopeViewModel: HoroscopeViewModel
    private val dateViewModel: DateViewModel by activityViewModels()

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

        dateViewModel.date.observe(viewLifecycleOwner) {
            createLayout()
        }

        return binding.root
    }

    private fun createLayout() {
        with(binding) {
            imageView.setImageDrawable(horoscopeViewModel.user.image)
            textName.text = horoscopeViewModel.user.name

            var counter = 0
            horoscopeViewModel.user.textMap.forEach {
                val title = TextView(requireContext())
                title.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                title.text = resources.getStringArray(R.array.horoscope_qualities)[counter]
                title.typeface = ResourcesCompat.getFont(requireContext(), R.font.jost_bold)
                title.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.text_title_size)
                )
                title.setPadding(resources.getDimensionPixelSize(R.dimen._8sdp))
                linearLayoutHoroscope.addView(title)

                val textViewSimple = TextView(requireContext())
                textViewSimple.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                textViewSimple.text = it.value
                textViewSimple.typeface =
                    ResourcesCompat.getFont(requireContext(), R.font.jost_regular)
                textViewSimple.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.text_simple_size)
                )
                textViewSimple.setPadding(resources.getDimensionPixelSize(R.dimen._8sdp))
                linearLayoutHoroscope.addView(textViewSimple)

                counter += 1
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}