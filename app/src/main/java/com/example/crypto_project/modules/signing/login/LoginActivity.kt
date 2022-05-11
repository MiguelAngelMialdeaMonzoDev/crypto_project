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

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }


    fun signing() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            viewModel.email.value.toString(),
            viewModel.password.value.toString()
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                goToMain()
            } else {
                showAlert()
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.error)
        builder.setMessage(R.string.error_login)
        builder.setPositiveButton(R.string.accept_positive_button, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}