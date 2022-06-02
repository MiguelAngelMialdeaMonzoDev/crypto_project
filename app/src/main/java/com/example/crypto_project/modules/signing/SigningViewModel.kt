package com.example.crypto_project.modules.signing

import android.content.ContentValues
import android.util.Log
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class SigningViewModel: ViewModel() {
    var callbackManager = CallbackManager.Factory.create()
    var auth: FirebaseAuth? = null

    fun loginFacebook(owner: ActivityResultRegistryOwner) {
        LoginManager.getInstance().logInWithReadPermissions(owner, callbackManager, listOf("email"))

        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    handleFacebookAccessToken(result.accessToken)
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                    error.localizedMessage?.let { it1 -> Log.e("Facebook error:", it1) }
                }
            }
        )
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(ContentValues.TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth?.signInWithCredential(credential)?.addOnCompleteListener {
            if (it.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                auth?.currentUser?.getIdToken(true)?.addOnCompleteListener { userAuth ->
                }
            } else {
                // If sign in fails, display a message to the user.
                Log.w(ContentValues.TAG, "signInWithCredential:failure", it.exception)
            }
        }
    }
}