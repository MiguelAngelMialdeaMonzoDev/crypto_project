package com.example.crypto_project.modules.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crypto_project.modules.signing.SigningActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToSigning()
    }

    private fun goToSigning() {
        startActivity(Intent(this, SigningActivity::class.java))
    }
}