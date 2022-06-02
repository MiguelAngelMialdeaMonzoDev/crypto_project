package com.example.crypto_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto_project.adapters.CoinsAdapter
import com.example.crypto_project.data.model.Coin
import com.example.crypto_project.databinding.ActivityMainBinding
import com.example.crypto_project.modules.main.crypto_profile.CryptoProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    lateinit var coinsAdapter: CoinsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
        initNavigation()
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.fragment_container)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_general_crypto,
                R.id.navigation_best_cryptos,
                R.id.navigation_worst_cryptos,
                R.id.navigation_exchanges
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }
}