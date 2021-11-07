package com.shouyinz.archdemo.page.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.shouyinz.archdemo.databinding.FragmentCurrencyListBinding
import com.shouyinz.archdemo.page.demo.DemoViewModel

class CurrencyListFragment: Fragment() {

    private lateinit var binding: FragmentCurrencyListBinding
    private val demoViewModel: DemoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        binding.rvCurrency.apply {
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }

    private fun observe() {
        demoViewModel.currencyList.observe(viewLifecycleOwner) { list ->
            binding.rvCurrency.apply {
                adapter = CurrencyListAdapter(
                    list
                ) { currency ->
                    demoViewModel.currencyCallback(currency)
                }
            }
        }
    }
}