package com.example.crypto_project.data.model

import com.example.crypto_project.api.Config

class Login {
    var access_token: String? = null
    val client_id = Config.CLIENT_ID
    val client_secret = Config.CLIENT_SECRET
    var grant_type: String? = null
    var refresh_token: String? = null
    var username: String? = null
    var password: String? = null
}