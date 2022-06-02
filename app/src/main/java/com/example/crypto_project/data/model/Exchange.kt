package com.example.crypto_project.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Exchange(
    val id: String? = null,
    val name: String? = null,
    val year_established: String? = null,
    val country: String? = null,
    val url: String? = null,
    val image: String? = null,
    val trust_score: Int? = null,
    @SerializedName("trade_volume_24h_btc")
    val trade_volume_btc_24: String? = null
    ) : Serializable