package com.example.cryto.presentation.listscreen.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.cryto.presentation.listscreen.adapter.itemdata.FavoritesDataItem
import com.example.extension.percentage
import com.example.home.databinding.ItemFavoritesBinding

class FavoritesHolder(
    private val binding: ItemFavoritesBinding,
    private val onContinue: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(itemData: FavoritesDataItem) {
        with(binding) {
            tvPairName.text = itemData.pairName
            tvLast.text = itemData.last
            val formattedDailyPercent = itemData.dailyPercent

            tvDailyPercent.text = formattedDailyPercent.percentage()

            tvDailyPercent.setTextColor(itemView.context.getColor(itemData.dailyPercentColor))

            itemView.setOnClickListener { onContinue() }
        }
    }
}