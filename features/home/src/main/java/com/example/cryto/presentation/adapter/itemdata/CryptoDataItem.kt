package com.example.cryto.presentation.adapter.itemdata

import com.example.base.SingleItemData

data class CryptoDataItem(
    override val id: String,
    val pair: String = "",
    val pairNormalized: String = "",
    val timestamp: Long = -1,
    val last: Double = 0.0,
    val high: Double = 0.0,
    val low: Double = 0.0,
    val bid: Double = 0.0,
    val ask: Double = 0.0,
    val open: Double = 0.0,
    val volume: Double = 0.0,
    val average: Double = 0.0,
    val daily: Double = 0.0,
    val dailyPercent: String = "",
    val denominatorSymbol: String = "",
    val numeratorSymbol: String = "",
    val order: Int = -1,
    val dailyPercentColor: Int = -1,
) : SingleItemData