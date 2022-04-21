package com.example.crypto_project.modules.signing.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.crypto_project.R
import com.example.crypto_project.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel

        setupToolbar()
        initObservers()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    private fun initObservers() {
        viewModel.email.observe(this) {
            viewModel.checkEmail()
        }

        viewModel.emailError.observe(this) { error ->
            binding.inputEmail.error = if (error != 0) getString(error) else null
        }

        viewModel.user.observe(this) {
            viewModel.checkUser()
        }

        viewModel.userError.observe(this) { error ->
            binding.inputUser.error = if (error != 0) getString(error) else null
        }

        viewModel.password.observe(this) {
            viewModel.checkPassword()
        }

        viewModel.passwordError.observe(this) { error ->
            binding.inputPassword.error = if (error != 0) getString(error) else null
        }

        viewModel.passwordConfirmation.observe(this) {
            viewModel.checkPasswordConfirmation()
        }

        viewModel.passwordConfirmationError.observe(this) { error ->
            binding.inputPasswordConfirmation.error = if (error != 0) getString(error) else null
        }
    }
}