package com.example.cryto.presentation.listscreen.adapter.itemdata

import com.example.base.SingleItemData

data class FavoritesDataItem(
    override val id: String,
    val pairName: String = "",
    val last: String = "",
    val dailyPercent: String = "",
    val dailyPercentColor: Int = -1,
) : SingleItemData