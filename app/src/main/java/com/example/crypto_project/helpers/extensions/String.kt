package com.example.crypto_project.helpers.extensions

import com.example.crypto_project.helpers.Constants.CP_PATTERN
import com.example.crypto_project.helpers.Constants.EMAIL_PATTERN
import com.example.crypto_project.helpers.Constants.NAME_PATTERN
import com.example.crypto_project.helpers.Constants.NIF_PATTERN
import com.example.crypto_project.helpers.Constants.PASSWORD_PATTERN
import com.example.crypto_project.helpers.Constants.PASSWORD_PATTERN_DIGITS
import com.example.crypto_project.helpers.Constants.PASSWORD_PATTERN_NUMBER
import com.example.crypto_project.helpers.Constants.PASSWORD_PATTERN_UPPER
import com.example.crypto_project.helpers.Constants.PHONE_PATTERN
import com.example.crypto_project.helpers.Constants.USERNAME_PATTERN

fun String.isValidName() = matches(NAME_PATTERN.toRegex())
fun String.isValidEmail() = matches(EMAIL_PATTERN.toRegex())
fun String.isValidUsername() = matches(USERNAME_PATTERN.toRegex())
fun String.isValidPassword() = matches(PASSWORD_PATTERN.toRegex())
fun String.isValidCP() = matches(CP_PATTERN.toRegex())
fun String.isValidNumberPass() = matches(PASSWORD_PATTERN_NUMBER.toRegex())
fun String.isValidLengthPass() = matches(PASSWORD_PATTERN_DIGITS.toRegex())
fun String.isValidUpperPass() = matches(PASSWORD_PATTERN_UPPER.toRegex())
fun String.isValidPhone() = matches(PHONE_PATTERN.toRegex())
fun String.isValidNif() = matches(NIF_PATTERN.toRegex())