package com.example.crypto_project.helpers

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(val context: Context) {

    private val PREF_FILE = "MyPreferences"

    private val ACCESS_TOKEN = "access_token"
    private val REFRESH_TOKEN = "refresh_token"
    private val SHOW_ONBOARDING = "show_onboarding"
    private val USER_ID = "user_id"
    private val FIREBASE_ID = "firebase_id"
    private val FIRST_TIME = "first_time"

    private fun getSharedPreferences(): SharedPreferences? {
        return context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
    }

    fun getAccessToken(): String? {
        return getSharedPreferences()?.getString(ACCESS_TOKEN, null)
    }

    fun setAccessToken(accessToken: String?) {
        this.getSharedPreferences()?.edit()?.putString(ACCESS_TOKEN, accessToken)?.apply()
    }

    fun removeAccessToken() {
        this.getSharedPreferences()?.edit()?.remove(ACCESS_TOKEN)?.apply()
    }

    fun getRefreshToken(): String? {
        return getSharedPreferences()?.getString(REFRESH_TOKEN, null)
    }

    fun setRefreshToken(refreshToken: String?) {
        this.getSharedPreferences()?.edit()?.putString(REFRESH_TOKEN, refreshToken)?.apply()
    }

    fun removeRefreshToken() {
        this.getSharedPreferences()?.edit()?.remove(REFRESH_TOKEN)?.apply()
    }

    fun isShowOnboarding(): Boolean? {
        return getSharedPreferences()?.getBoolean(SHOW_ONBOARDING, true)
    }

    fun setShowOnboarding(show: Boolean) {
        this.getSharedPreferences()?.edit()?.putBoolean(SHOW_ONBOARDING, show)?.apply()
    }

    fun getUserId(): String? {
        return getSharedPreferences()?.getString(USER_ID, null)
    }

    fun setUserId(userId: String?) {
        this.getSharedPreferences()?.edit()?.putString(USER_ID, userId)?.apply()
    }

    fun getFirebaseId(): String? {
        return getSharedPreferences()?.getString(FIREBASE_ID, null)
    }

    fun setFirebaseId(firebaseId: String?) {
        this.getSharedPreferences()?.edit()?.putString(FIREBASE_ID, firebaseId)?.apply()
    }

    fun getFirstTime(): Boolean? {
        return getSharedPreferences()?.getBoolean(FIRST_TIME, true)
    }

    fun setFirstTime(isFirstTime: Boolean) {
        this.getSharedPreferences()?.edit()?.putBoolean(FIRST_TIME, isFirstTime)?.apply()
    }

    fun clearPreferences() {
        getSharedPreferences()?.edit()?.clear()?.apply()
    }
}