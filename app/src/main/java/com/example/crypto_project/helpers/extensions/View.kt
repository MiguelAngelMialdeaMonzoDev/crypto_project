package com.example.crypto_project.helpers.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.showWithAnimation() = apply {
    visibility = View.VISIBLE
    alpha = 0.0f
    animate().alpha(1.0f).setListener(null)
}

fun View.hideWithAnimation() = apply {
    animate().alpha(0.0f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            visibility = View.GONE
        }
    }
    )
}