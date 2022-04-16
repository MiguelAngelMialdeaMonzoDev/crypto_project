package com.example.crypto_project.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.crypto_project.BuildConfig
import com.example.crypto_project.R
import com.example.crypto_project.data.model.Coin
import com.example.crypto_project.databinding.ItemCoinBinding
import java.math.RoundingMode

class CoinsAdapter(
    private val coinClickListener: CoinCLickListener
) : ListAdapter<Coin, CoinsAdapter.ViewHolder>(ListAdapterCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), coinClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder constructor(
        val binding: ItemCoinBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Coin,
            clickListener: CoinCLickListener
        ) {
            binding.coin = item
            binding.textName.text = item.name

            Glide.with(binding.root.context)
                .load(item.image.small)
                .encodeQuality(100)
                .into(binding.imageCoin)

            item.marketData.changePercentage =
                item.marketData.changePercentage?.toBigDecimal()?.setScale(2, RoundingMode.UP)
                    .toString()
            item.marketData.currentPrice.dollars =
                item.marketData.currentPrice.dollars?.toBigDecimal()?.setScale(4, RoundingMode.UP)
                    .toString()
            binding.textPrice.text = item.marketData.currentPrice.dollars
            with(binding.root.context) {
                when {
                    item.marketData.changePercentage == "0.00" -> {
                        binding.textPercentage.text = item.marketData.changePercentage+"%"
                        binding.textPercentage.setTextColor(getColor(R.color.black))
                    }
                    item.marketData.changePercentage.toString().contains("-") -> {
                        binding.textPercentage.text = item.marketData.changePercentage+"%"
                        binding.textPercentage.setTextColor(getColor(R.color.red))
                    }
                    else -> {
                        binding.textPercentage.text = "+"+item.marketData.changePercentage+"%"
                        binding.textPercentage.setTextColor(getColor(R.color.green))
                    }
                }
            }
        }
    }

    class ListAdapterCallback : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin) =
            (oldItem.id == newItem.id)


        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Coin, newItem: Coin) =
            (oldItem == newItem)
    }

    interface CoinCLickListener {
        fun onClick(item: Coin)
    }
}