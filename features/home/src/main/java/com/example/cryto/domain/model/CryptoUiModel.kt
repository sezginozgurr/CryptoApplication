package com.example.cryto.domain.model

data class CryptoUiModel(
    val pair: String = "",
    val pairNormalized: String = "",
    val timestamp: Long = -1,
    val last: Long = -1,
    val high: Long = -1,
    val low: Long = -1,
    val bid: Long = -1,
    val ask: Long = -1,
    val open: Long = -1,
    val volume: Double = 0.0,
    val average: Double = 0.0,
    val daily: Int = -1,
    val dailyPercent: Double = 0.0,
    val denominatorSymbol: String = "",
    val numeratorSymbol: String = "",
    val order: Int = -1,
)
