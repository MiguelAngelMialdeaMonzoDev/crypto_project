package com.example.crypto_project.data.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("usd")
    var dollars: String?
)