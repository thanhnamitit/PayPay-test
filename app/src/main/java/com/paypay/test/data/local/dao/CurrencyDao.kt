package com.paypay.test.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.paypay.test.data.local.entity.CurrencyEntity

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getAllCurrencies(): List<CurrencyEntity>

    @Insert
    fun insertAll(vararg entities: CurrencyEntity)
}