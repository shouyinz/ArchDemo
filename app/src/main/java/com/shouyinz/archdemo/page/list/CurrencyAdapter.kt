package com.shouyinz.archdemo.page.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shouyinz.archdemo.databinding.ViewCurrencyBinding
import com.shouyinz.archdemo.vo.Currency

class CurrencyListAdapter(
    private val currencyList: ArrayList<Currency>,
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            ViewCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = currencyList.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    override fun onViewRecycled(holder: CurrencyViewHolder) {
        holder.recycled()
    }
}

class CurrencyViewHolder(
    private val binding: ViewCurrencyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currency: Currency) {
        binding.tvName.text = currency.name
        binding.tvSymbol.text = currency.symbol
    }

    fun recycled() {
    }
}