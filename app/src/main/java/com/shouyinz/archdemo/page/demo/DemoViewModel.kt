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

class DemoViewModel: ViewModel() {

    val showProgress = MutableLiveData(false)
    val currencyList = MutableLiveData<ArrayList<Currency>>(ArrayList())

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
}