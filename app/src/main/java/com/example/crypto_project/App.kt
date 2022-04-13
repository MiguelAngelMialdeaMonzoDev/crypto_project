package com.example.crypto_project

import android.app.Application
import com.example.crypto_project.helpers.AppPreferences

class App : Application() {

    companion object {
        lateinit var instance: App private set
        lateinit var preferences: AppPreferences
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        preferences = AppPreferences(applicationContext)
    }
}