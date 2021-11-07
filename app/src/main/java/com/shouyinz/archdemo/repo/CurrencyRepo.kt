package com.shouyinz.archdemo.repo

import com.shouyinz.archdemo.db.CurrencyDatabase
import com.shouyinz.archdemo.vo.Currency

class CurrencyRepo(
    private val database: CurrencyDatabase
) {
    suspend fun init() {
        val currencyDao = database.currencyDao()
        currencyDao.deleteAll()

        currencyDao.insert(Currency("BTC", "Bitcoin", "BTC"))
        currencyDao.insert(Currency("ETH", "Ethereum", "ETH"))
        currencyDao.insert(Currency("XRP", "XRP", "XRP"))
        currencyDao.insert(Currency("BCH", "Bitcoin Cash", "BCH"))
        currencyDao.insert(Currency("LTC", "Litecoin", "LTC"))
        currencyDao.insert(Currency("EOS", "EOS", "EOS"))
        currencyDao.insert(Currency("BNB", "Binance Coin", "BNB"))
        currencyDao.insert(Currency("LINK", "Chianlink", "LINK"))
        currencyDao.insert(Currency("NEO", "NEO", "NEO"))
        currencyDao.insert(Currency("ETC", "Ethereum Classic", "ETC"))
        currencyDao.insert(Currency("ONT", "Ontology", "ONT"))
        currencyDao.insert(Currency("CRO", "Crypto.com Chain", "CRO"))
        currencyDao.insert(Currency("CUC", "Cucumber", "CUC"))
        currencyDao.insert(Currency("USDC", "USD Coin", "USDC"))
    }
}