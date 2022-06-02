package com.example.crypto_project.modules.signing.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.crypto_project.MainActivity
import com.example.crypto_project.R
import com.example.crypto_project.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

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

        binding.toolbar.setOnClickListener { onBackPressed() }
        initObservers()
    }

    fun setUp() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            viewModel.email.value.toString(),
            viewModel.password.value.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                goToHome()
            } else {
                showAlert()
            }
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Se ha producido un error al registrarse, intentelo de nuevo más tarde.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun initObservers() {
        viewModel.email.observe(this) {
            viewModel.checkEmail()
            viewModel.checkFieldsCorrect()
        }

        viewModel.emailError.observe(this) { error ->
            binding.inputEmail.error = if (error != 0) getString(error) else null
        }

        viewModel.user.observe(this) {
            viewModel.checkUser()
            viewModel.checkFieldsCorrect()
        }

        viewModel.userError.observe(this) { error ->
            binding.inputUser.error = if (error != 0) getString(error) else null
        }

        viewModel.password.observe(this) {
            viewModel.checkPassword()
            viewModel.checkFieldsCorrect()
        }

        viewModel.passwordError.observe(this) { error ->
            binding.inputPassword.error = if (error != 0) getString(error) else null
        }

        viewModel.passwordConfirmation.observe(this) {
            viewModel.checkPasswordConfirmation()
            viewModel.checkFieldsCorrect()
        }

        viewModel.passwordConfirmationError.observe(this) { error ->
            binding.inputPasswordConfirmation.error = if (error != 0) getString(error) else null
        }

        viewModel.fieldsCorrect.observe(this) { isCorrect ->
            if (isCorrect) {
                binding.buttonRegister.alpha = 1f
            } else {
                binding.buttonRegister.alpha = 0.5f
            }
        }
    }
}