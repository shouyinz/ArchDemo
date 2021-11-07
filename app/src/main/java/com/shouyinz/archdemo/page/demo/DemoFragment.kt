package com.shouyinz.archdemo.page.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.shouyinz.archdemo.databinding.FragmentDemoBinding
import com.shouyinz.archdemo.db.CurrencyDatabase
import com.shouyinz.archdemo.repo.CurrencyRepo

class DemoFragment : Fragment() {

    private lateinit var binding: FragmentDemoBinding
    private lateinit var toast: Toast
    private val demoViewModel: DemoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDemoBinding.inflate(inflater, container, false)
        toast = Toast(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        binding.btnLoadData.setOnClickListener {
            demoViewModel.init(
                CurrencyRepo(CurrencyDatabase.getDatabase(requireContext()))
            )
        }
    }

    private fun observe() {
        demoViewModel.showProgress.observe(viewLifecycleOwner) { show ->
            binding.progress.visibility = if (show) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        demoViewModel.onCurrencyClicked.observe(viewLifecycleOwner) { currency ->
            toast.cancel()
            toast = Toast.makeText(
                requireContext(),
                currency.name,
                Toast.LENGTH_SHORT
            ).apply {
                show()
            }
        }
    }
}