package com.shouyinz.archdemo.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shouyinz.archdemo.databinding.FragmentDemoBinding

class DemoFragment : Fragment() {

    private lateinit var binding: FragmentDemoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDemoBinding.inflate(inflater, container, false)
        return binding.root
    }
}