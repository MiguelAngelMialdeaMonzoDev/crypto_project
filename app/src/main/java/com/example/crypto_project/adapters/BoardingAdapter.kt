package com.example.crypto_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto_project.R
import com.example.crypto_project.data.model.BoardingItem

class BoardingAdapter(private val boardingItems: List<BoardingItem>) :
    RecyclerView.Adapter<BoardingAdapter.OnboardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_onboarding,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(boardingItems[position])
    }

    override fun getItemCount(): Int {
        return boardingItems.size
    }

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageOnboarding = view.findViewById<ImageView>(R.id.image_onboarding)
        private val textTitle = view.findViewById<TextView>(R.id.text_title_onboarding)
        private val textDescription = view.findViewById<TextView>(R.id.text_body_onboarding)

        fun bind(boardingItem: BoardingItem) {
            imageOnboarding.setImageResource(boardingItem.onboardingImage)
            textTitle.text = boardingItem.title
            textDescription.text = boardingItem.description
        }
    }
}