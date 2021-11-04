package com.wiserax.zodiac.ui.psychomatrix

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentPsychomatrixBinding

class PsychomatrixFragment : Fragment() {

    private lateinit var viewModel: PsychomatrixViewModel
    private var _binding: FragmentPsychomatrixBinding? = null
    private lateinit var matrix: Array<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("fragment", "Psycho onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this)[PsychomatrixViewModel::class.java]

        _binding = FragmentPsychomatrixBinding.inflate(inflater, container, false)
        val root: View = binding.root


        with(binding) {
            textView1Temper.text = viewModel.matrixCells.value?.get(1)
            textView2Charisma.text = viewModel.matrixCells.value?.get(2)
            textView3Knowledge.text = viewModel.matrixCells.value?.get(3)
            textView4Health.text = viewModel.matrixCells.value?.get(4)
            textView5Logic.text = viewModel.matrixCells.value?.get(5)
            textView6Mastery.text = viewModel.matrixCells.value?.get(6)
            textView7Luck.text = viewModel.matrixCells.value?.get(7)
            textView8SenseOfDuty.text = viewModel.matrixCells.value?.get(8)
            textView9Intelligence.text = viewModel.matrixCells.value?.get(9)

            var counter = 0
            viewModel.matrixText.value?.forEach {
                val textTitle = TextView(requireContext())
                textTitle.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                val titleText = resources.getStringArray(R.array.human_qualities)[counter]
                val titleTextWithDigits =
                    SpannableString(titleText + " " + (viewModel.matrixCells.value?.get(counter+1) ?: ""))
                titleTextWithDigits.setSpan(
                    ForegroundColorSpan(resources.getColor(R.color.orange)),
                    titleText.length,
                    titleTextWithDigits.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textTitle.text = titleTextWithDigits
                textTitle.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen._24ssp)
                )
                textTitle.setPadding(resources.getDimensionPixelSize(R.dimen._8sdp))
                linearLayoutInScrollView.addView(textTitle)

                val textSimple = TextView(requireContext())
                textSimple.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                textSimple.text = it.value
                textSimple.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen._16ssp)
                )
                textSimple.setPadding(resources.getDimensionPixelSize(R.dimen._8sdp))
                linearLayoutInScrollView.addView(textSimple)

                counter += 1
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}