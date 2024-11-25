package com.example.cryto.presentation.adapter.itemdata

import com.example.base.SingleItemData

data class FavoritesDataItem(
    override val id: String,
    val pairName: String = "",
    val last: String = "",
    val dailyPercent: String = "",
) : SingleItemData