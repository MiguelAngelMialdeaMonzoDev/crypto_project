package com.example.crypto_project.modules.signing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.crypto_project.R
import com.example.crypto_project.databinding.ActivitySigningBinding
import com.example.crypto_project.modules.signing.login.LoginActivity
import com.example.crypto_project.modules.signing.register.RegisterActivity

class SigningActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigningBinding
    private lateinit var viewModel: SigningViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signing)
        viewModel = ViewModelProvider(this).get(SigningViewModel::class.java)

        binding.activity = this
    }

    fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}