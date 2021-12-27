package com.paypay.test.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExchangeRatesResponse(
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("quotes")
    val quotes: Map<String, String>?
)