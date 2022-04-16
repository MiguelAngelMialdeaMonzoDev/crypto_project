package com.example.crypto_project.data.model

import com.google.gson.annotations.SerializedName

data class Volume(
    @SerializedName("usd")
    var usdVolume: String?
)