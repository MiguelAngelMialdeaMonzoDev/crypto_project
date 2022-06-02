package com.example.crypto_project.modules.signing.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto_project.R

class LoginViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val emailError = MutableLiveData<Int>()
    val password = MutableLiveData<String>()
    val passwordError = MutableLiveData<Int>()
    val fieldsCorrect = MutableLiveData(false)

    fun checkEmail() {
        if (email.value.isNullOrEmpty()) {
            emailError.value = R.string.empty_field
        } else {
            emailError.value = 0
        }
    }

    fun checkPassword() {
        if (password.value.isNullOrEmpty()) {
            passwordError.value = R.string.empty_field
        } else {
            passwordError.value = 0
        }
    }

    fun checkFieldsCorrect() {
        fieldsCorrect.value = passwordError.value == 0 && emailError.value == 0
    }
}