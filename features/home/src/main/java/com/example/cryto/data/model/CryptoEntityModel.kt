package com.example.cryto.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptoEntityModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pairName: String = "",
    val last: String = "",
    val dailyPercent: String = "",
)
