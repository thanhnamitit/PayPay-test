package com.paypay.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paypay.test.data.local.dao.CurrencyDao
import com.paypay.test.data.local.entity.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}