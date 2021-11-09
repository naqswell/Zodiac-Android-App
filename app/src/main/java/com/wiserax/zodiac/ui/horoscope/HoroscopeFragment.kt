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
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentHoroscopeBinding

class HoroscopeFragment : Fragment() {

    private lateinit var viewModel: HoroscopeViewModel

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel =
            ViewModelProvider(this)[HoroscopeViewModel::class.java]

        _binding = FragmentHoroscopeBinding.inflate(inflater, container, false)

        with(binding) {

            imageView.setImageDrawable(viewModel.user.image)
            textName.text = viewModel.user.name

            var counter = 0
            viewModel.user.textMap.forEach{
                val title = TextView(requireContext())
                title.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                title.text = resources.getStringArray(R.array.horoscope_qualities)[counter]
                title.typeface = ResourcesCompat.getFont(requireContext(), R.font.jost_bold)
                title.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen._24ssp)
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
                    resources.getDimension(R.dimen._16ssp)
                )
                textViewSimple.setPadding(resources.getDimensionPixelSize(R.dimen._8sdp))
                linearLayoutHoroscope.addView(textViewSimple)

                counter += 1
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}