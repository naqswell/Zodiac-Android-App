package com.wiserax.zodiac.ui.compatibility

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.navigation.fragment.navArgs
import com.wiserax.zodiac.model.JsonReader
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentCompatibility2Binding
import java.lang.NullPointerException

class CompatibilityFragment2 : Fragment() {

    private val args: CompatibilityFragment2Args by navArgs()

    private val sign1: Sign by lazy { Sign.valueOf(args.title1.toString()) }
    private val sign2: Sign by lazy { Sign.valueOf(args.title2.toString()) }

    private var _binding: FragmentCompatibility2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompatibility2Binding.inflate(inflater, container, false)

        with(binding) {
            imageViewSign1.setImageResource(sign1.image)
            textViewTitle1.text = sign1.title
            textViewDates1.text = sign1.dates

            imageViewSign2.setImageResource(sign2.image)
            textViewTitle2.text = sign2.title
            textViewDates2.text = sign2.dates

            loadCompatibilityInfo(linearLayoutText)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadCompatibilityInfo(layout: LinearLayout) {
        val application = activity?.application ?: throw NullPointerException("Activity is null")
        val reader = JsonReader(application)

        val (textMap, percentagesMap) = reader.getCompatibility(sign1, sign2)

        textMap.forEach { (title, description) ->
            if (title == application.resources.getString(R.string.general)) {
                binding.textViewGeneralTitle.text = title
                binding.textViewGeneralDescription.text = description
            } else {
                val titleTextView = createTextView(title, R.dimen.text_title_size, true)
                val descriptionTextView = createTextView(description, R.dimen.text_simple_size, false)

                layout.addView(titleTextView)
                layout.addView(descriptionTextView)
            }
        }

        percentagesMap.forEach { (key, value) ->
            val percentage = "$value%"

            when (key) {
                "generalPower" -> binding.textViewPercentage.text = percentage
                "lovePower" -> binding.textViewLove.text = percentage
                "friendshipPower" -> binding.textViewFriendship.text = percentage
                "workPower" -> binding.textViewWork.text = percentage
                "familyPower" -> binding.textViewFamily.text = percentage
            }
        }
    }

    private fun createTextView(text: String, textSizeDimenId: Int, isBold: Boolean): TextView {
        val textView = TextView(requireContext())
        val font = if (isBold) R.font.jost_bold else R.font.jost_regular
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

//        params.bottomMargin = 8
//        params.bottomMargin = resources.getDimensionPixelSize(R.dimen._8sdp)

        textView.layoutParams = params
        textView.text = text
        textView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(textSizeDimenId)
        )
        textView.typeface = ResourcesCompat.getFont(requireContext(), font)
        textView.setPadding(resources.getDimensionPixelSize(R.dimen._8sdp))
        return textView
    }
}