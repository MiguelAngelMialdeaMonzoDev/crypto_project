package com.example.crypto_project.api

import com.example.crypto_project.data.model.Coin
import com.example.crypto_project.data.model.Exchange
import com.example.crypto_project.data.model.Login
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    // LOGIN
    @POST("auth/token/")
    suspend fun postLogin(@Body login: Login): Response<Token>

    // GET ALL CRYPTOS
    @GET("coins/markets?vs_currency=USD&order=market_cap_desc&per_page=4000&page=1&sparkline=false")
    suspend fun getAllCryptos(): Response<ArrayList<Coin>>

    // GET ALL EXCHANGES
    @GET("exchanges?per_page=250")
    suspend fun getAllExchanges(): Response<ArrayList<Exchange>>
}