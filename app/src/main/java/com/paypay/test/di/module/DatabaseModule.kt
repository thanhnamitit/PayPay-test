package com.paypay.test.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.paypay.test.data.local.AppDatabase
import com.paypay.test.data.local.dao.CurrencyDao
import com.paypay.test.data.local.repository.CurrencyLocalRepository
import com.paypay.test.data.local.repository.CurrencyLocalRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database"
        ).build()
    }

    @Provides
    fun provideCurrencyDao(database: AppDatabase): CurrencyDao {
        return database.currencyDao()
    }

    @Provides
    fun bindRepository(input: CurrencyLocalRepositoryImpl): CurrencyLocalRepository {
        return input
    }


    @Provides
    fun providePreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    }
}