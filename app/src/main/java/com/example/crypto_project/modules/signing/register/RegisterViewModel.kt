package com.example.crypto_project.modules.signing.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto_project.R
import com.example.crypto_project.helpers.extensions.isValidEmail
import com.example.crypto_project.helpers.extensions.isValidPassword

class RegisterViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val user = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordConfirmation = MutableLiveData<String>()

    val emailError = MutableLiveData<Int>()
    val userError = MutableLiveData<Int>()
    val passwordError = MutableLiveData<Int>()
    val passwordConfirmationError = MutableLiveData<Int>()
    val fieldsCorrect = MutableLiveData(false)

    fun checkEmail() {
        when {
            email.value.isNullOrEmpty() -> emailError.value = R.string.empty_field
            !email.value.toString().isValidEmail() -> emailError.value = R.string.email_error
            else -> emailError.value = 0
        }
    }

    fun checkUser() {
        when {
            user.value.isNullOrEmpty() -> userError.value = R.string.empty_field
            user.value.toString().length < 3 -> userError.value = R.string.user_length
            else -> userError.value = 0
        }
    }

    fun checkPassword() {
        when {
            password.value.isNullOrEmpty() -> passwordError.value = R.string.empty_field
            !password.value.toString().isValidPassword() -> passwordError.value =
                R.string.password_error
            else -> passwordError.value = 0
        }
    }

    fun checkPasswordConfirmation() {
        when {
            passwordConfirmation.value.isNullOrEmpty() -> passwordConfirmationError.value = R.string.empty_field
            !passwordConfirmation.value.equals(password.value) -> passwordConfirmationError.value = R.string.register_password_confirmation_error
            else -> passwordConfirmationError.value = 0
        }
    }

    fun checkFieldsCorrect() {
        fieldsCorrect.value = emailError.value == 0 && userError.value == 0 && passwordError.value == 0 && passwordConfirmationError.value == 0
    }
}