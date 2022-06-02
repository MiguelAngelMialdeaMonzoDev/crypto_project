package com.example.crypto_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_project.api.RetrofitClient
import com.example.crypto_project.data.model.Coin
import com.example.crypto_project.data.model.Exchange
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {
    val coinsList = MutableLiveData<ArrayList<Coin>>()
    val retrofitClient = RetrofitClient()
    val exchangeList = MutableLiveData<ArrayList<Exchange>>()
    val showLoading = MutableLiveData(false)

    fun getCoinsList() {
        showLoading.value = true
        viewModelScope.launch {
            retrofitClient.apiCall({
                retrofitClient.getAllCryptos()
            },
                object : RetrofitClient.RemoteEmitter {
                    override fun onResponse(response: Response<Any>) {
                        coinsList.value = response.body() as ArrayList<Coin>
                        showLoading.value = false
                    }

                    override fun onError(errorType: RetrofitClient.ErrorType, msg: String) {
                    }

                })
        }
    }

    fun getExchangesList() {
        showLoading.value = true
        viewModelScope.launch {
            retrofitClient.apiCall({
                retrofitClient.getAllExchanges()
            },
                object : RetrofitClient.RemoteEmitter {
                    override fun onResponse(response: Response<Any>) {
                        exchangeList.value = response.body() as ArrayList<Exchange>
                        showLoading.value = false
                    }

                    override fun onError(errorType: RetrofitClient.ErrorType, msg: String) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }
}