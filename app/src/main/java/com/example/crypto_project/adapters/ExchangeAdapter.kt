package com.example.crypto_project.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crypto_project.R
import com.example.crypto_project.data.model.Exchange
import com.example.crypto_project.databinding.ItemExchangeBinding

class ExchangeAdapter(
    private val exchangeClickListener: ExchangeClickListener
) : ListAdapter<Exchange, ExchangeAdapter.ViewHolder>(ListAdapterCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), exchangeClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemExchangeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder constructor(
        val binding: ItemExchangeBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            exchange: Exchange,
            clickListener: ExchangeClickListener
        ) {
            binding.exchange = exchange

            Glide.with(binding.root.context)
                .load(exchange.image)
                .encodeQuality(100)
                .into(binding.imageExchange)

            binding.textYear.text = binding.root.context.getString(R.string.exchange_created_in, exchange.year_established, exchange.country)
            binding.textVolume.text = exchange.trade_volume_btc_24
            binding.textRating.text = binding.root.context.getString(R.string.exchange_rating_stars, exchange.trust_score.toString())

            binding.textUrl.setOnClickListener {
                clickListener.onClick(exchange)
            }
        }
    }

    class ListAdapterCallback : DiffUtil.ItemCallback<Exchange>() {
        override fun areItemsTheSame(oldItem: Exchange, newItem: Exchange) =
            (oldItem.id == newItem.id)


        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Exchange, newItem: Exchange) =
            (oldItem == newItem)
    }

    interface ExchangeClickListener {
        fun onClick(item: Exchange)
    }
}