package com.paypay.test.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
class CurrencyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "first_name") val code: String,
    @ColumnInfo(name = "last_name") val name: String
)