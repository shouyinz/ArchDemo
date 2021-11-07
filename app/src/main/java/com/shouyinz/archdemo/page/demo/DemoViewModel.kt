package com.shouyinz.archdemo.page.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shouyinz.archdemo.repo.CurrencyRepo
import com.shouyinz.archdemo.vo.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class DemoViewModel : ViewModel() {

    val showProgress = MutableLiveData(false)
    val currencyList = MutableLiveData<ArrayList<Currency>>(ArrayList())
    val onCurrencyClicked = MutableLiveData<Currency>()

    fun init(repo: CurrencyRepo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                showProgress.postValue(true)
                repo.init()
                currencyList.postValue(ArrayList(repo.fetchCurrency()))
                showProgress.postValue(false)
            }
        }
    }

    fun currencyCallback(currency: Currency) {
        onCurrencyClicked.postValue(currency)
    }

    fun sort() {
        ArrayList<Currency>().apply {
            currencyList.value?.let {
                addAll(it)
            }
            sortWith(Comparator { p0, p1 ->
                if (p0 == null || p1 == null) {
                    0
                } else {
                    p0.symbol.compareTo(p1.symbol)
                }
            })
            currencyList.postValue(this)
        }
    }
}