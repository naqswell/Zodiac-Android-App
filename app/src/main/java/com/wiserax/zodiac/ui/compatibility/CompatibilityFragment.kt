package com.wiserax.zodiac.ui.compatibility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wiserax.zodiac.databinding.FragmentCompatibilityBinding

class CompatibilityFragment : Fragment() {

    private lateinit var compatibilityViewModel: CompatibilityViewModel
    private var _binding: FragmentCompatibilityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        compatibilityViewModel =
            ViewModelProvider(this).get(CompatibilityViewModel::class.java)

        _binding = FragmentCompatibilityBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}