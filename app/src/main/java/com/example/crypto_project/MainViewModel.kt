package com.example.crypto_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_project.api.RetrofitClient
import com.example.crypto_project.data.model.Coin
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {
    val coinsList = MutableLiveData<ArrayList<Coin>>()
    val retrofitClient = RetrofitClient()

    fun getCoinsList() {
        viewModelScope.launch {
            retrofitClient.apiCall({
                retrofitClient.getAllCryptos()
            },
            object : RetrofitClient.RemoteEmitter {
                override fun onResponse(response: Response<Any>) {
                    coinsList.value = response.body() as ArrayList<Coin>
                }

                override fun onError(errorType: RetrofitClient.ErrorType, msg: String) {
                }

            })
        }
    }
}