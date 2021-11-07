package com.shouyinz.archdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shouyinz.archdemo.vo.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getCurrencyList(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(currency: Currency)

    @Query("DELETE FROM currency")
    suspend fun deleteAll()
}