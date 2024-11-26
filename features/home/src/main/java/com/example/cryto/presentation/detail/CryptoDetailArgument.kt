package com.example.cryto.presentation.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoDetailArgument(
    val pairName: String = "",
    val last: Double = 0.0,
    val high: Double = 0.0,
    val low: Double = 0.0,
    val bid: Double = 0.0,
    val ask: Double = 0.0,
    val open: Double = 0.0,
    val volume: Double = 0.0,
    val average: Double = 0.0
) : Parcelable
