package com.example.cryto.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.base.SingleListAdapterDiffUtil
import com.example.cryto.presentation.adapter.holder.CryptoHolder
import com.example.cryto.presentation.adapter.itemdata.CryptoDataItem
import com.example.home.databinding.ItemCryptoBinding

class CryptoListAdapter(
    private val onContinue: () -> Unit,
    private val addFavorites: (pairName: String, last: String, dailyPercent: String) -> Unit,
) :
    ListAdapter<CryptoDataItem, CryptoHolder>(
        SingleListAdapterDiffUtil()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        return CryptoHolder(
            ItemCryptoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onContinue,
            addFavorites
        )

    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(getItem(position))
    }
}