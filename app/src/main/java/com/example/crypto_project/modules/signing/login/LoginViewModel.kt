package com.example.crypto_project.modules.signing.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto_project.R
import com.example.crypto_project.helpers.extensions.isValidEmail
import com.example.crypto_project.helpers.extensions.isValidPassword

class LoginViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    private val emailError = MutableLiveData(0)
    val password = MutableLiveData<String>()
    private val passwordError = MutableLiveData(0)

    fun checkEmail() {
        when {
            email.value.isNullOrEmpty() -> emailError.value = R.string.empty_field
            email.value.toString().isValidEmail() -> emailError.value = R.string.email_error
            else -> emailError.value = 0
        }
    }

    fun checkPassword() {
        when {
            password.value.isNullOrEmpty() -> passwordError.value = R.string.empty_field
            password.value.toString().isValidPassword() -> passwordError.value =
                R.string.password_error
            else -> passwordError.value = 0
        }
    }

    fun checkAllFields() {
        if (passwordError.value == 0 && emailError.value == 0) {

        }
    }
}