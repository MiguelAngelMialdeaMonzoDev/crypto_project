package com.example.crypto_project.data.model

import com.google.gson.annotations.SerializedName

data class Coin(
    var id: String?,
    var symbol: String?,
    var name: String?,
    var image: Image,
    @SerializedName("market_data")
    var marketData: MarketData
)