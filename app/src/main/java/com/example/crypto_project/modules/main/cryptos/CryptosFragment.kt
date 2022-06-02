package com.example.crypto_project.modules.main.cryptos

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.crypto_project.MainActivity
import com.example.crypto_project.MainViewModel
import com.example.crypto_project.R
import com.example.crypto_project.adapters.CoinsAdapter
import com.example.crypto_project.data.model.Coin
import com.example.crypto_project.databinding.FragmentCryptosBinding
import com.example.crypto_project.modules.main.crypto_profile.CryptoProfileActivity

class CryptosFragment : Fragment() {
    private lateinit var binding: FragmentCryptosBinding
    private val parentViewModel by activityViewModels<MainViewModel>()
    private lateinit var coinsAdapter: CoinsAdapter
    private val parentActivity = MainActivity()

    private val responseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val arrayList =
                    activityResult.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                var voice = arrayList?.get(0)
                binding.search.onActionViewExpanded()
                binding.search.setQuery("" + voice, true)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cryptos, container, false)

        parentViewModel.getCoinsList()
        initAdapter()
        initObservers()
        initSearchView()
        initMicro()
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

        binding.recyclerCoins.adapter = coinsAdapter
    }

    private fun initMicro() {
        binding.imageVoice.setOnClickListener {
            openVoiceDialog()
        }
    }

    private fun initSearchView() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    val listFiltered = parentViewModel.coinsList.value?.filter { coin ->
                        coin.name?.lowercase()?.contains(newText.lowercase()) == true
                    }
                    coinsAdapter.submitList(listFiltered)
                } else {
                    coinsAdapter.submitList(parentViewModel.coinsList.value)
                }
                return false
            }
        })
    }

    private fun initObservers() {
        parentViewModel.coinsList.observe(viewLifecycleOwner) { coinsList ->
            coinsAdapter.submitList(coinsList)
        }
    }

    private fun openVoiceDialog() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        responseLauncher.launch(intent)
    }
}