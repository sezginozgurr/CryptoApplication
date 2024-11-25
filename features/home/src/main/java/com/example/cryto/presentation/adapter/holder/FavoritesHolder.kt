package com.example.cryto.presentation.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.cryto.presentation.adapter.itemdata.FavoritesDataItem
import com.example.home.databinding.ItemFavoritesBinding

class FavoritesHolder(
    private val binding: ItemFavoritesBinding,
    private val onContinue: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(itemData: FavoritesDataItem) {
        with(binding) {
            //tvPairName.text = itemData.pairNormalized
            tvLast.text = itemData.last.toString()
            val formattedDailyPercent = itemData.dailyPercent

            //tvDailyPercent.text = formattedDailyPercent.percentage()

            //tvDailyPercent.setTextColor(itemView.context.getColor(itemData.dailyPercentColor))

            //tvDaily.text = itemData.volume with itemData.numeratorSymbol
            itemView.setOnClickListener { onContinue() }
        }
    }
}