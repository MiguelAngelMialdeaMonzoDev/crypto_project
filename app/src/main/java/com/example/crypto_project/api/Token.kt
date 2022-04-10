package com.example.crypto_project.api

class Token {
    var access_token: String = ""
    var refresh_token: String = ""

    override fun toString(): String {
        return "$access_token $refresh_token"
    }
}