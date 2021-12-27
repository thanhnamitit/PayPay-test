package com.paypay.test.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrenciesResponse(
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("currencies")
    val currencies: Map<String, String>
)