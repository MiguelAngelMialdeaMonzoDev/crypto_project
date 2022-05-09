package com.example.crypto_project.modules.signing.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

        setUp()
    }


    fun setUp() {
        title = "Authentication"

        if (!viewModel.email.value.isNullOrEmpty() && !viewModel.password.value.isNullOrEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(viewModel.email.value.toString(), viewModel.password.value.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    showHome()
                    Toast.makeText(this, "it works!", Toast.LENGTH_SHORT).show()
                } else {
                    showAlert()
                }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome() {

    }
}