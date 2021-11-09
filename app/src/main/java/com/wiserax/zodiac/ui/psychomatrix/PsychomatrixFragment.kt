package com.wiserax.zodiac.ui.psychomatrix

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.R
import com.wiserax.zodiac.databinding.FragmentPsychomatrixBinding


class PsychomatrixFragment : Fragment() {

    private lateinit var viewModel: PsychomatrixViewModel

    private var _binding: FragmentPsychomatrixBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[PsychomatrixViewModel::class.java]

        _binding = FragmentPsychomatrixBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val matrixResIdArray: MutableMap<Int, String> = mutableMapOf()
        with(binding) {
            var counter = 0
            viewModel.matrixCells.forEach {
                if (it.key < 10) {
                    val textView = TextView(requireContext())
                    textView.setBackgroundResource(R.drawable.img_psychomatrix_square)
                    val param = GridLayout.LayoutParams()
                    param.height = resources.getDimensionPixelSize(R.dimen._85sdp)
                    param.width = resources.getDimensionPixelSize(R.dimen._85sdp)
                    param.rightMargin = resources.getDimensionPixelSize(R.dimen._4sdp)
                    param.topMargin = resources.getDimensionPixelSize(R.dimen._4sdp)
                    param.setGravity(Gravity.CENTER)
                    textView.layoutParams = param
                    val str =
                        it.value + "\n" + resources.getStringArray(R.array.human_qualities_two_row)[counter]
                    val id = View.generateViewId()
                    matrixResIdArray[id] = str
                    textView.id = id
                    textView.text = str
                    textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.jost_bold)
                    textView.gravity = Gravity.CENTER
                    gridMatrixCells.addView(textView)
                }
                counter += 1
            }

            counter = 0
            viewModel.matrixText.forEach {
                val title = TextView(requireContext())
                title.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                val titleText = resources.getStringArray(R.array.human_qualities)[counter]
                title.text = titleText
                title.typeface = ResourcesCompat.getFont(requireContext(), R.font.jost_bold)
                title.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.text_title_size)
                )
                title.setPadding(resources.getDimensionPixelSize(R.dimen._8sdp))
                linearLayoutInScrollView.addView(title)

                val titleAddition = TextView(requireContext())
                titleAddition.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var titleAddition2: TextView? = null

                if (it.key < 10) {
                    val titleAdditionString =
                        SpannableString(
                            (viewModel.matrixCells.get(counter + 1) ?: "")
                        )
                    titleAdditionString.setSpan(
                        ForegroundColorSpan(resources.getColor(R.color.orange, requireActivity().theme)),
                        0,
                        titleAdditionString.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    titleAddition.text = titleAdditionString
                } else {
                    titleAddition.text =
                        resources.getStringArray(R.array.human_qualities_addition)[counter]

                    titleAddition2 = TextView(requireContext())
                    titleAddition2.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    titleAddition2.typeface =
                        ResourcesCompat.getFont(requireContext(), R.font.jost_bold)
                    titleAddition2.setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        resources.getDimension(R.dimen.text_simple_size)
                    )
                    val titleAdditionString2 =
                        SpannableString(viewModel.matrixCells.get(it.key).toString())
                    titleAdditionString2.setSpan(
                        ForegroundColorSpan(resources.getColor(R.color.orange, requireActivity().theme)),
                        0,
                        titleAdditionString2.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    titleAddition2.text = titleAdditionString2
                }

                titleAddition.typeface =
                    ResourcesCompat.getFont(requireContext(), R.font.jost_bold)
                titleAddition.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimension(R.dimen.text_simple_size)
                )
                titleAddition.setPadding(resources.getDimensionPixelSize(R.dimen._8sdp))
                linearLayoutInScrollView.addView(titleAddition)
                if (titleAddition2 != null) {
                    linearLayoutInScrollView.addView(titleAddition2)
                }

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
                linearLayoutInScrollView.addView(textViewSimple)

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