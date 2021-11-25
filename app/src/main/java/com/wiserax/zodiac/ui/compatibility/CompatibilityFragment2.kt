package com.wiserax.zodiac.ui.compatibility

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.navArgs
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentCompatibility2Binding

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
        val application = requireActivity().application

        val compatibility = Compatibility(application, sign1, sign2)
        val percentagesMap = compatibility.percentages
        val articlesMap = compatibility.articles

        percentagesMap.forEach { (key, value) ->
            val percentage = "$value%"

            when (key) {
                "lovePower" -> binding.textViewLove.text = percentage
                "friendshipPower" -> binding.textViewFriendship.text = percentage
                "workPower" -> binding.textViewWork.text = percentage
                "familyPower" -> binding.textViewFamily.text = percentage
            }
        }

        articlesMap.forEach { (title, description) ->
            val titleView =
                if (title == application.resources.getString(R.string.general)) {
                    val generalPercentage = compatibility.generalPercentage
                    createTitleLayout(title, generalPercentage, R.dimen.text_title_size)
                } else {
                    createTextView(
                        text = title,
                        textSize = R.dimen.text_title_size,
                        isBold = true,
                        verticalMargin = 16,
                        gravity = Gravity.CENTER_HORIZONTAL
                    )
            }

            val descriptionView = createTextView(description, R.dimen.text_simple_size, false)

            layout.addView(titleView)
            layout.addView(descriptionView)
        }
    }

    private fun createTitleLayout(title: String, percentage: Int, textSize: Int): LinearLayout {
        val percentageText = "$percentage%"

        val layout = LinearLayout(requireContext())

        val titleTextView = createTextView(
            text = title,
            textSize = textSize,
            isBold = true,
            verticalMargin = 16,
            gravity = Gravity.END,
            weight = 1f
        )

        val spacer = Space(requireContext())
        spacer.layoutParams = LinearLayout.LayoutParams(16, 0)

        val percentageTextView = createTextView(
            text = percentageText,
            textSize = textSize,
            isBold = true,
            verticalMargin = 16,
            gravity = Gravity.START,
            weight = 1f
        )

        val percentageColor = resources.getColor(
            R.color.orange, null
        )
        percentageTextView.setTextColor(percentageColor)

        layout.addView(titleTextView)
        layout.addView(spacer)
        layout.addView(percentageTextView)

        return layout
    }

    private fun createTextView(
        text: String,
        textSize: Int,
        isBold: Boolean,
        verticalMargin: Int = 0,
        gravity: Int = Gravity.NO_GRAVITY,
        weight: Float = 0f
    ): TextView {
        val textView = TextView(requireContext())

        val font = if (isBold) R.font.jost_bold else R.font.jost_regular

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = verticalMargin
        params.bottomMargin = verticalMargin / 2
        params.weight = weight

        textView.layoutParams = params
        textView.text = text
        textView.gravity = gravity
        textView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(textSize)
        )
        textView.typeface = ResourcesCompat.getFont(requireContext(), font)

        return textView
    }
}