package com.shouyinz.archdemo.page.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shouyinz.archdemo.repo.CurrencyRepo
import com.shouyinz.archdemo.vo.Currency
import kotlinx.coroutines.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class DemoViewModel : ViewModel() {

    val showProgress = MutableLiveData(false)
    val currencyList = MutableLiveData<ArrayList<Currency>>(ArrayList())
    val onCurrencyClicked = MutableLiveData<Currency>()

    private var flag: Boolean = false

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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                showProgress.postValue(true)
                ArrayList<Currency>().apply {
                    currencyList.value?.let {
                        addAll(it)
                    }
                    sortWith(Comparator { p0, p1 ->
                        if (p0 == null || p1 == null) {
                            0
                        } else {
                            if (flag) {
                                p0.symbol.compareTo(p1.symbol)
                            } else {
                                p1.symbol.compareTo(p0.symbol)
                            }
                        }
                    })
                    flag = !flag
                    // Dummy delay to simulate IO
                    delay(3000)
                    currencyList.postValue(this)
                    showProgress.postValue(false)
                }
            }
        }
    }
}