package com.example.crypto_project.modules.main.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.crypto_project.MainViewModel
import com.example.crypto_project.R
import com.example.crypto_project.adapters.ExchangeAdapter
import com.example.crypto_project.data.model.Exchange
import com.example.crypto_project.databinding.FragmentExchangesBinding

class ExchangeFragment : Fragment() {
    private lateinit var binding: FragmentExchangesBinding
    private val parentViewModel by activityViewModels<MainViewModel>()
    private lateinit var exchangeAdapter: ExchangeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exchanges, container, false)

        parentViewModel.getExchangesList()
        initAdapter()
        initObservers()

        return binding.root
    }

    private fun initAdapter() {
        exchangeAdapter = ExchangeAdapter(object : ExchangeAdapter.ExchangeClickListener {
            override fun onClick(item: Exchange) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
            }
        })

        binding.recyclerExchanges.adapter = exchangeAdapter
    }

    private fun initObservers() {
        parentViewModel.exchangeList.observe(viewLifecycleOwner) { exchangeList ->
            exchangeAdapter.submitList(exchangeList)
        }
    }
}