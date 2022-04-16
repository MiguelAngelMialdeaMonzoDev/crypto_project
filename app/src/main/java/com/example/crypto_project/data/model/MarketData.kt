package com.example.crypto_project.data.model

import com.google.gson.annotations.SerializedName

data class MarketData(
    @SerializedName("current_price")
    var currentPrice: Price,
    /*@SerializedName("market_cap")
    var marketCap: ArrayList<MarketCap>,
    @SerializedName("market_cap_rank")
    var marketCapRank: Int = 0,
    @SerializedName("total_volume")
    var totalVolume: ArrayList<Volume>,*/
    @SerializedName("price_change_percentage_24h")
    var changePercentage: String?
)