package com.example.cryto.data.model

import kotlinx.serialization.SerialName

data class CryptoResponseModel(
    @SerialName("pair")
    val pair: String = "",

    @SerialName("pairNormalized")
    val pairNormalized: String = "",

    @SerialName("timestamp")
    val timestamp: Long = -1,

    @SerialName("last")
    val last: Long = -1,

    @SerialName("high")
    val high: Long = -1,

    @SerialName("low")
    val low: Long = -1,

    @SerialName("bid")
    val bid: Long = -1,

    @SerialName("ask")
    val ask: Long = -1,

    @SerialName("open")
    val open: Long = -1,

    @SerialName("volume")
    val volume: Double = 0.0,

    @SerialName("average")
    val average: Double = 0.0,

    @SerialName("daily")
    val daily: Int = -1,

    @SerialName("dailyPercent")
    val dailyPercent: Double = 0.0,

    @SerialName("denominatorSymbol")
    val denominatorSymbol: String = "",

    @SerialName("numeratorSymbol")
    val numeratorSymbol: String = "",

    @SerialName("order")
    val order: Int = -1,
)
