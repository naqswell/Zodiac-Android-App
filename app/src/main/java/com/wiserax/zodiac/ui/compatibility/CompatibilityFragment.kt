package com.wiserax.zodiac.ui.compatibility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.wiserax.zodiac.databinding.FragmentCompatibilityBinding

class CompatibilityFragment : Fragment() {

//    private val compatibilityViewModel: CompatibilityViewModel by viewModels()

    private var _binding: FragmentCompatibilityBinding? = null
    private val binding get() = _binding!!

    private val adapter1 by lazy { SignAdapter() }
    private val adapter2 by lazy { SignAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompatibilityBinding.inflate(inflater, container, false)

        binding.viewPager1.instantiate(adapter1)
        binding.viewPager2.instantiate(adapter2)

        binding.buttonCheckCompatibility.setOnClickListener {
            val position1 = binding.viewPager1.currentItem
            val position2 = binding.viewPager2.currentItem

            val title1 = adapter1.getCurrentTitle(position1)
            val title2 = adapter1.getCurrentTitle(position2)

            val action = CompatibilityFragmentDirections.actionCompatibilityToCompabilityFragment2(title1, title2)

            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun ViewPager2.instantiate(adapter: SignAdapter) {
        this.adapter = adapter

        setCurrentItem(Sign.values().size / 2, false)

        clipToPadding = false
        clipChildren = false
        offscreenPageLimit = 3

        val transformer = CompositePageTransformer()

        transformer.addTransformer(MarginPageTransformer(10))
        setPageTransformer(transformer)
    }
}