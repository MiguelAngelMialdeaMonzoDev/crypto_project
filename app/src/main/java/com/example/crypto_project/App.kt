package com.example.crypto_project

import android.app.Application
import com.example.crypto_project.helpers.AppPreferences
import com.example.crypto_project.helpers.NavigationHelper

class App : Application() {

    companion object {
        lateinit var instance: App private set
        lateinit var preferences: AppPreferences
        lateinit var navigationHelper: NavigationHelper
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        navigationHelper = NavigationHelper()
        preferences = AppPreferences(applicationContext)
    }
}