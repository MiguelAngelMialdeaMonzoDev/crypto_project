package com.example.crypto_project.modules.signing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.crypto_project.MainActivity
import com.example.crypto_project.R
import com.example.crypto_project.databinding.ActivitySigningBinding
import com.example.crypto_project.helpers.Constants.GOOGLE_CODE
import com.example.crypto_project.helpers.Constants.REQUEST_CODE_GOOGLE_LOGIN
import com.example.crypto_project.modules.signing.login.LoginActivity
import com.example.crypto_project.modules.signing.register.RegisterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigningActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigningBinding
    private lateinit var viewModel: SigningViewModel
    private lateinit var auth: FirebaseAuth

    private val responseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    account?.let {
                        firebaseAuthWithGoogle(it)
                    }
                } catch (e: ApiException) {
                    Log.w("Error", "Google sign in failed", e)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signing)
        viewModel = ViewModelProvider(this).get(SigningViewModel::class.java)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel

        auth = Firebase.auth
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.getIdToken(true)?.addOnCompleteListener {
                        if (it.isSuccessful)
                            goToHome()
                    }
                } else {
                    Log.w("Error", "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun loginFacebook() {
        showFacebookProgress(true)
        viewModel.loginFacebook(this)
    }

    fun loginGoogle() {
        showGoogleProgress(true)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1039537046109-c8rd41eiloenj73cpdm9abj3r06vljn6.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val client: GoogleSignInClient = GoogleSignIn.getClient(this, gso)
        client.signOut()

        val intent = client.signInIntent.apply {
            putExtra(GOOGLE_CODE, REQUEST_CODE_GOOGLE_LOGIN)
        }
        responseLauncher.launch(intent)
    }

    private fun showGoogleProgress(show: Boolean) {
        if (show) {
            binding.buttonLoginGoogle.text = ""
            binding.buttonLoginGoogle.isEnabled = false
            binding.progressLoginGoogle.visibility = View.VISIBLE
        } else {
            binding.buttonLoginGoogle.text = getString(R.string.signing_text_google)
            binding.buttonLoginGoogle.isEnabled = true
            binding.progressLoginGoogle.visibility = View.GONE
        }
    }

    private fun showFacebookProgress(show: Boolean) {
        if (show) {
            binding.buttonLoginFacebook.text = ""
            binding.buttonLoginFacebook.isEnabled = false
            binding.progressLoginFacebook.visibility = View.VISIBLE
        } else {
            binding.buttonLoginFacebook.text = getString(R.string.signing_text_facebook)
            binding.buttonLoginFacebook.isEnabled = true
            binding.progressLoginFacebook.visibility = View.GONE
        }
    }

    fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}