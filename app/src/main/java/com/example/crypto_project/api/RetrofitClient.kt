package com.example.crypto_project.api

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.example.crypto_project.App
import com.example.crypto_project.BuildConfig
import com.example.crypto_project.data.model.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException

class RetrofitClient {

    enum class ErrorType {
        HTTP_EXCEPTION, // HTTP
        NETWORK, // IO
        TIMEOUT, // Socket
        UNKNOWN // Anything else
    }

    interface RemoteEmitter {
        fun onResponse(response: Response<Any>)
        fun onError(errorType: ErrorType, msg: String)
    }

    private val clientWithoutAuth by lazy {
        Retrofit.Builder().baseUrl(Config.API_URL).client(
            OkHttpClient().newBuilder().addInterceptor(
                LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BODY)
                    .request("Request")
                    .response("Response")
                    .addHeader("Content-Type", "application/json")
                    .build()
            ).build()
        )
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }

    suspend inline fun <T> apiCall(
        crossinline responseFunction: suspend () -> T,
        emitter: RemoteEmitter
    ) {
        try {
            val response = withContext(Dispatchers.IO) { responseFunction.invoke() }
            withContext(Dispatchers.Main) {
                emitter.onResponse(response as Response<Any>)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                when (e) {
                    is HttpException -> {
                        val body = e.response()?.errorBody().toString()
                        emitter.onError(ErrorType.HTTP_EXCEPTION, body)
                    }
                    is SocketTimeoutException -> emitter.onError(
                        ErrorType.TIMEOUT,
                        "Timeout Error"
                    )
                    is IOException -> emitter.onError(ErrorType.NETWORK, "Thread Error")
                    else -> emitter.onError(ErrorType.UNKNOWN, "Unknown Error")
                }
            }
        }
    }

    suspend fun postLogin(login: Login) = clientWithoutAuth.postLogin(login)
    suspend fun getAllCryptos() = clientWithoutAuth.getAllCryptos()
    suspend fun getAllExchanges() = clientWithoutAuth.getAllExchanges()
}