package com.example.cryto.presentation.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.base.globalExt.with
import com.example.cryto.presentation.adapter.itemdata.CryptoDataItem
import com.example.extension.percentage
import com.example.home.databinding.ItemCryptoBinding

class CryptoHolder(
    private val binding: ItemCryptoBinding,
    private val onContinue: () -> Unit,
    private val addFavorites: (pairName: String, last: String, dailyPercent: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(itemData: CryptoDataItem) {
        with(binding) {
            tvPairName.text = itemData.pairNormalized
            tvLast.text = itemData.last.toString()
            val formattedDailyPercent = itemData.dailyPercent

            tvDailyPercent.text = formattedDailyPercent.percentage()

            tvDailyPercent.setTextColor(itemView.context.getColor(itemData.dailyPercentColor))

            tvDaily.text = itemData.volume with itemData.numeratorSymbol
            itemView.setOnClickListener { onContinue() }
            ivStar.setOnClickListener {
                addFavorites(
                    itemData.pairNormalized, itemData.last.toString(), itemData.dailyPercent
                )
            }
        }
    }
}