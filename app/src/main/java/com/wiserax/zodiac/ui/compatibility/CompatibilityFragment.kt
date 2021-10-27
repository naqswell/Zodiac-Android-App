package com.wiserax.zodiac.ui.compatibility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.wiserax.zodiac.databinding.FragmentCompabilityBinding
import com.wiserax.zodiac.ui.psychomatrix.SignAdapter
import kotlin.math.abs

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
        val recyclerView2 = binding.recyclerView2

//        recyclerView1.layoutManager =
//            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        viewPager1.offscreenPageLimit = 3
        viewPager1.clipToPadding = false
        viewPager1.clipChildren = false
        viewPager1.getChildAt(4) //.overScrollMode(View.OVER_SCROLL_NEVER)

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(8))

        transformer.addTransformer { page, position ->
            val v = abs(position)
            page.scaleY = 0.8f + v * 0.2f
        }

        viewPager1.setPageTransformer(transformer)
        viewPager1.adapter = SignAdapter()

        recyclerView2.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.adapter = SignAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}