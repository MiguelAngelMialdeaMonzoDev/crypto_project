package com.example.crypto_project.modules.signing.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.crypto_project.R
import com.example.crypto_project.adapters.BoardingAdapter
import com.example.crypto_project.data.model.BoardingItem
import com.example.crypto_project.databinding.ActivityBoardingBinding
import com.example.crypto_project.modules.signing.SigningActivity

class BoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardingBinding
    private lateinit var viewModel: BoardingViewModel
    private lateinit var boardingAdapter: BoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        setTheme(R.style.Theme_Crypto_project)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boarding)
        viewModel = ViewModelProvider(this).get(BoardingViewModel::class.java)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel
        initAdapter()
        initObservers()
        changeViewPager()
    }

    private fun changeViewPager() {
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.buttonFinish.visibility = View.VISIBLE
                } else {
                    binding.buttonFinish.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun initObservers() {
        viewModel.count.observe(this) {
            Toast.makeText(this, ""+it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapter() {
        boardingAdapter = BoardingAdapter(
            listOf(
                BoardingItem(
                    R.drawable.image_logo,
                    getString(R.string.first_title),
                    getString(R.string.first_description)
                ),
                BoardingItem(
                    R.drawable.image_logo,
                    getString(R.string.second_title),
                    getString(R.string.second_description)
                ),
                BoardingItem(
                    R.drawable.image_logo,
                    getString(R.string.third_title),
                    getString(R.string.third_description)
                )
            )
        )
        binding.viewpager.adapter = boardingAdapter
        binding.circleIndicator.setViewPager(binding.viewpager)
    }

    fun goToSigning() {
        startActivity(Intent(this, SigningActivity::class.java))
    }
}