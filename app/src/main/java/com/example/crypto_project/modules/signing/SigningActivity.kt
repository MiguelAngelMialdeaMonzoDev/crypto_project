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
        Thread.sleep(1000)
        setTheme(R.style.Theme_Crypto_project)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signing)
        viewModel = ViewModelProvider(this).get(SigningViewModel::class.java)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel

    }

    fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}