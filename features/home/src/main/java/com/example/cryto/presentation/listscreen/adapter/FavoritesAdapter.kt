package com.example.cryto.presentation.listscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.base.SingleListAdapterDiffUtil
import com.example.cryto.presentation.listscreen.adapter.holder.FavoritesHolder
import com.example.cryto.presentation.listscreen.adapter.itemdata.FavoritesDataItem
import com.example.home.databinding.ItemFavoritesBinding

class FavoritesAdapter(private val onContinue: () -> Unit) :
    ListAdapter<FavoritesDataItem, FavoritesHolder>(
        SingleListAdapterDiffUtil()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        return FavoritesHolder(
            ItemFavoritesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onContinue
        )

    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        holder.bind(getItem(position))
    }
}