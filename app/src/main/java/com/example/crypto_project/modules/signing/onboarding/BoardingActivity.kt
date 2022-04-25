package com.example.crypto_project.modules.signing.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crypto_project.R
import com.example.crypto_project.adapters.BoardingAdapter
import com.example.crypto_project.data.model.BoardingItem

class BoardingActivity : AppCompatActivity() {
    private lateinit var boardingAdapter: BoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)
    }

    private fun initAdapter() {
        boardingAdapter = BoardingAdapter(
            listOf(
            )
        )
    }
}