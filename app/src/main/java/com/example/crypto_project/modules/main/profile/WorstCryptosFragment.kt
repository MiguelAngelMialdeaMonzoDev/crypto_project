package com.example.crypto_project.modules.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.crypto_project.MainActivity
import com.example.crypto_project.MainViewModel
import com.example.crypto_project.R
import com.example.crypto_project.adapters.CoinsAdapter
import com.example.crypto_project.data.model.Coin
import com.example.crypto_project.databinding.FragmentWorstCryptosBinding
import com.example.crypto_project.modules.main.crypto_profile.CryptoProfileActivity

class WorstCryptosFragment : Fragment() {
    private lateinit var binding: FragmentWorstCryptosBinding
    private val parentViewModel by activityViewModels<MainViewModel>()
    private lateinit var coinsAdapter: CoinsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_worst_cryptos, container, false)

        initAdapter()
        filterList()
        return binding.root
    }

    private fun initAdapter() {
        coinsAdapter = CoinsAdapter(object : CoinsAdapter.CoinCLickListener {
            override fun onClick(item: Coin) {
                startActivity(
                    Intent(requireContext(), CryptoProfileActivity::class.java)
                        .putExtra("COIN", item))
            }
        })

        binding.recyclerBestCryptos.adapter = coinsAdapter
    }

    private fun filterList() {
        val listFiltered = parentViewModel.coinsList.value?.sortedBy { it.changePercentage?.toFloat() }
        coinsAdapter.submitList(listFiltered)
    }
}