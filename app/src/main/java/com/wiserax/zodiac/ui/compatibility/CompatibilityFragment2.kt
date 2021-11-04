package com.wiserax.zodiac.ui.compatibility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.navArgs
import com.wiserax.zodiac.JsonReader
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentCompability2Binding
import com.wiserax.zodiac.model.Sign
import java.lang.NullPointerException

class CompatibilityFragment2 : Fragment() {

    private val args: CompatibilityFragment2Args by navArgs()

    private val sign1: Sign by lazy { Sign.valueOf(args.title1.toString()) }
    private val sign2: Sign by lazy { Sign.valueOf(args.title2.toString()) }

    private var _binding: FragmentCompability2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompability2Binding.inflate(inflater, container, false)

        with(binding) {
            imageViewSign1.setImageResource(sign1.image)
            textViewTitle1.text = sign1.title
            textViewDates1.text = sign1.dates

            imageViewSign2.setImageResource(sign2.image)
            textViewTitle2.text = sign2.title
            textViewDates2.text = sign2.dates

            loadTextTo(linearLayoutText)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadTextTo(layout: LinearLayout) {
        val application = activity?.application ?: throw NullPointerException("Activity is null")
        val reader = JsonReader(application)
        val map = reader.getCompatibilityTextBySignsPair(sign1, sign2)

        map.forEach { (key, value) ->
            when (key) {
                application.resources.getString(R.string.general) -> {
                    binding.textViewGeneralTitle.text = key
                    binding.textViewGeneralDescription.text = value
                }
                "generalPower" -> {
                    val text = "$value%"
                    binding.textViewPercentage.text = text
                }
                "lovePower" -> {
                    val text = "$value%"
                    binding.textViewLove.text = text
                }
                "friendshipPower" -> {
                    val text = "$value%"
                    binding.textViewFriendship.text = text
                }
                "workPower" -> {
                    val text = "$value%"
                    binding.textViewWork.text = text
                }
                "familyPower" -> {
                    val text = "$value%"
                    binding.textViewFamily.text = text
                }
                else -> {
                    val title = createTextView(key, 20f, true)
                    val description = createTextView(value, 16f, false)

                    layout.addView(title)
                    layout.addView(description)
                }
            }
        }
    }

    private fun createTextView(text: String, size: Float, isBold: Boolean): TextView {
        val textView = TextView(requireContext())
        val font = if (isBold) R.font.jost_bold else R.font.jost_regular
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        params.bottomMargin = 8

        textView.layoutParams = params
        textView.text = text
        textView.textSize = size
        textView.typeface = ResourcesCompat.getFont(requireContext(), font)
        textView.setTextColor(resources.getColor(R.color.black, null))

        return textView
    }
}