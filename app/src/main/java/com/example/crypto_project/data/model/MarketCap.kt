package com.example.crypto_project.data.model

import com.google.gson.annotations.SerializedName

data class MarketCap(
    @SerializedName("usd")
    var dollarMarketCap: String?
)