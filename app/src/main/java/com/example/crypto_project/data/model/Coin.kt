package com.example.crypto_project.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Coin(
    var id: String?,
    var symbol: String?,
    var name: String?,
    var image: String?,
    @SerializedName("current_price")
    var currentPrice: String?,
    @SerializedName("market_cap")
    var marketCap: String?,
    @SerializedName("market_cap_rank")
    var marketCapRank: String?,
    @SerializedName("total_volume")
    var totalVolume: String?,
    @SerializedName("price_change_percentage_24h")
    var changePercentage: String?
): Serializable