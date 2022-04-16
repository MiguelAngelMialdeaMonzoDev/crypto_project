package com.example.crypto_project.modules.main.cryptos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.crypto_project.MainViewModel
import com.example.crypto_project.R
import com.example.crypto_project.adapters.CoinsAdapter
import com.example.crypto_project.data.model.Coin
import com.example.crypto_project.databinding.FragmentCryptosBinding

class CryptosFragment : Fragment() {
    private lateinit var binding : FragmentCryptosBinding
    private val parentViewModel by activityViewModels<MainViewModel>()
    private lateinit var coinsAdapter: CoinsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cryptos, container, false)

        initAdapter()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        parentViewModel.coinsList.observe(viewLifecycleOwner) { coinsList ->
            coinsAdapter.submitList(coinsList)
        }
    }

    private fun initAdapter() {
        parentViewModel.getCoinsList()
        coinsAdapter = CoinsAdapter(object : CoinsAdapter.CoinCLickListener {
            override fun onClick(item: Coin) {
                TODO("Not yet implemented")
            }
        })
        binding.recyclerCoins.adapter = coinsAdapter
    }

    companion object {
        fun newInstance() = CryptosFragment()
    }
}