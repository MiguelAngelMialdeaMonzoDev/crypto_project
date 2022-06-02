package com.example.crypto_project.modules.signing.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.crypto_project.MainActivity
import com.example.crypto_project.R
import com.example.crypto_project.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.activity = this

        initObservers()
        binding.toolbar.setOnClickListener { onBackPressed() }
    }

    private fun initObservers() {
        viewModel.email.observe(this) {
            viewModel.checkEmail()
            viewModel.checkFieldsCorrect()
        }

        viewModel.emailError.observe(this) { error ->
            binding.inputEmail.error = if (error != 0) getString(error) else null
        }

        viewModel.password.observe(this) {
            viewModel.checkPassword()
            viewModel.checkFieldsCorrect()
        }

        viewModel.passwordError.observe(this) { error ->
            binding.inputPassword.error = if (error != 0) getString(error) else null
        }

        viewModel.fieldsCorrect.observe(this) { isCorrect ->
            if (isCorrect) {
                binding.buttonLogin.alpha = 1f
            } else {
                binding.buttonLogin.alpha = 0.5f
            }
        }
    }


    fun setUp() {
        if (!viewModel.email.value.isNullOrEmpty() && !viewModel.password.value.isNullOrEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(viewModel.email.value.toString(), viewModel.password.value.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    goToHome()
                } else {
                    showAlert()
                }
            }
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("No existe ningún usuario con estas credenciales.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}