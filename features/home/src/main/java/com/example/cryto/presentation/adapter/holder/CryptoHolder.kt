package com.example.cryto.presentation.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.cryto.presentation.adapter.itemdata.CryptoDataItem
import com.example.home.databinding.ItemCryptoBinding

class CryptoHolder(private val binding: ItemCryptoBinding, private val onContinue: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(itemData: CryptoDataItem) {
        binding.tvLast.text = itemData.pairNormalized
        binding.tvPairName.text = itemData.pair
        binding.tvDailyPercent.text = itemData.dailyPercent.toString()
    }
}