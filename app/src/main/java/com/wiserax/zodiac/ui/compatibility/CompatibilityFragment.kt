package com.wiserax.zodiac.ui.compatibility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.databinding.FragmentCompabilityBinding
import com.wiserax.zodiac.model.Sign

class CompatibilityFragment : Fragment() {

    private lateinit var compatibilityViewModel: CompatibilityViewModel

    private var _binding: FragmentCompabilityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        compatibilityViewModel =
            ViewModelProvider(this).get(CompatibilityViewModel::class.java)

        _binding = FragmentCompabilityBinding.inflate(inflater, container, false)

        val viewPager1 = binding.viewPager1
        val viewPager2 = binding.viewPager2

        viewPager1.adapter = SignAdapter()
        viewPager1.currentItem = Sign.values().size / 2 + 1
        viewPager1.offscreenPageLimit = 3
        viewPager1.clipToPadding = false
        viewPager1.clipChildren = false

        viewPager2.adapter = SignAdapter()
        viewPager2.currentItem = Sign.values().size / 2 - 1
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}