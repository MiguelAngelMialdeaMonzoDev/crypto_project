package com.example.crypto_project.helpers

object Constants {
    // REGEXP PATTERNS
    const val USERNAME_PATTERN = "^[\\w.@+-]+\\Z"
    const val NAME_PATTERN = "^[ÁÑÉÍÓÚA-Z][a-záñéíóú]+(\\s+[ÁÑÉÍÓÚA-Z]?[a-záñéíóú]+)*\$"
    const val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z._-]+\\.+[a-z]+"
    const val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[A-ZÑÁÉÍÓÚ]).{8,})"
    const val CP_PATTERN = "^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}\$"
    const val PASSWORD_PATTERN_DIGITS = "(.{6,})"
    const val PASSWORD_PATTERN_UPPER = "(.*[A-ZÑÁÉÍÓÚ].*)"
    const val PASSWORD_PATTERN_NUMBER = "(.*[0-9].*)"
    const val PHONE_PATTERN = "^(\\+(?:[0-9]{1,3}))?s?(?:6[0-9]|7[0-9]|9[0-9])[0-9]\\s?[0-9]{3}\\s?[0-9]{3}\$"
    const val NIF_PATTERN = "^[XYZ]?\\d{5,8}[A-Z]\$"

    // DATE AND TIME PATTERNS
    const val SHORT_DATE_PATTERN = "dd MMM"
    const val MEDIUM_DATE_PATTERN = "dd/MM/yyyy"
    const val MEDIUM_MONTH_DATE_PATTERN = "dd/MMM/yyyy"
    const val SERVER_DATE_PATTERN = "yyyy/MM/dd"
    const val SERVER_DATE_OWN = "yyyy/dd/MM"
    const val SERVER_DATE_PICKER_PATTERN = "yyyy/mm/dd"
    const val SERVER_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
    const val SERVER_USER_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ"
    const val IMAGE_DATETIME_PATTERN = "yyyyMMdd_HHmmss"

    const val HOUR_PATTERN = "HH:mm"
    const val LONG_HOUR_PATTERN = "HH:mm:ss"
    const val LONG_DATETIME_PATTERN = "dd/MM/yyyy • hh:mm aa"

    // SOCIAL LOGIN
    const val LOGIN_FACEBOOK = "facebook"
    const val LOGIN_GOOGLE = "google"
    const val REQUEST_CODE_GOOGLE_LOGIN = 1006
    const val GOOGLE_CODE = "GOOGLE_CODE"

    // WEBSERVICES CODES
    const val SERVER_SUCCESS_CODE = 200
    const val SERVER_CREATED_CODE = 201
    const val SERVER_NOCONTENT_CODE = 204
    const val SERVER_BADREQUEST_CODE = 400
    const val SERVER_UNAUTHORIZED_CODE = 401
    const val SERVER_FORBIDDEN_CODE = 403
    const val SERVER_NOTFOUND_CODE = 404
    const val SERVER_TIMEOUT_CODE = 408
    const val SERVER_CONFLICT_CODE = 409
    const val SERVER_INTERNALSERVER_CODE = 500
    const val SERVER_BANNED_CODE = 403

    // DIALOG CODES
    const val BUNDLE_KEY_TITLE = "title"
    const val BUNDLE_KEY_DESCRIPTION = "description"
    const val BUNDLE_KEY_ACCEPT = "accept"
    const val BUNDLE_KEY_CANCEL = "cancel"
    const val BUNDLE_KEY_NON_CANCELABLE = "non_cancelable"
    const val BUNDLE_KEY_DISMISS_CLICK = "disable_dismiss"
    const val BUNDLE_KEY_SHOW_IBAN = "show_iban"
    const val BUNDLE_KEY_SHOW_IBAN_TEXT = "show_iban_text"
    const val BUNDLE_KEY_SHOW_CLOSED_GAME = "show_closed_game"
    const val BUNDLE_KEY_SHOW_WARNING = "show_warning"

    // GENERIC
    const val MIN_YEAR_ALLOWED = 18

}