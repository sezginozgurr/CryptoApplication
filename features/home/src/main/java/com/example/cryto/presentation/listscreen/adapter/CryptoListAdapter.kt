package com.example.cryto.presentation.listscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.base.SingleListAdapterDiffUtil
import com.example.cryto.presentation.listscreen.adapter.holder.AdapterClick
import com.example.cryto.presentation.listscreen.adapter.holder.CryptoHolder
import com.example.cryto.presentation.listscreen.adapter.itemdata.CryptoDataItem
import com.example.home.databinding.ItemCryptoBinding

class CryptoListAdapter(private val adapterClick: AdapterClick) :
    ListAdapter<CryptoDataItem, CryptoHolder>(SingleListAdapterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        return CryptoHolder(
            ItemCryptoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            adapterClick
        )

    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(getItem(position))
    }
}