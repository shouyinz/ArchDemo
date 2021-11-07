package com.shouyinz.archdemo.page.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shouyinz.archdemo.repo.CurrencyRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel: ViewModel() {

    val showProgress = MutableLiveData(false)

    fun init(repo: CurrencyRepo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                showProgress.postValue(true)
                repo.init()
                showProgress.postValue(false)
            }
        }
    }
}