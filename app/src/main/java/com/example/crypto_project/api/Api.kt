package com.example.crypto_project.api

import com.example.crypto_project.data.model.Login
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    // REFRESH TOKEN
    @POST("auth/token/")
    fun refreshToken(@Body login: Login): Call<Token>

    // LOGIN
    @POST("auth/token/")
    suspend fun postLogin(@Body login: Login): Response<Token>

}