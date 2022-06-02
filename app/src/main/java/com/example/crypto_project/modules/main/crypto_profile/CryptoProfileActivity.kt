package com.example.crypto_project.modules.main.crypto_profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.crypto_project.R
import com.example.crypto_project.data.model.Coin
import com.example.crypto_project.databinding.ActivityCryptoProfileBinding

class CryptoProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCryptoProfileBinding
    private lateinit var viewModel: CryptoProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_crypto_profile)

        initProfile()

        binding.toolbar.setOnClickListener { onBackPressed() }
    }

    private fun initProfile() {
        val coinData = intent.getSerializableExtra("COIN") as Coin
        Glide.with(binding.root.context)
            .load(coinData.image)
            .encodeQuality(100)
            .into(binding.imageCoin)

        binding.textName.text = coinData.name
        binding.textTicker.text = coinData.symbol
        binding.textPrice.text = coinData.currentPrice + " $"
        binding.textRanking.text = coinData.marketCapRank
        binding.textVolume.text = coinData.totalVolume
        binding.textMarketCap.text = coinData.marketCap
    }
}