package com.example.crypto_project.modules.main.cryptos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.crypto_project.R

class CryptosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cryptos, container, false)
    }

    companion object {
        fun newInstance() = CryptosFragment()
    }
}