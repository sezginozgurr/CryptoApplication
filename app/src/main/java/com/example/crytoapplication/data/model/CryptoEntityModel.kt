package com.example.crytoapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptoEntityModel(
    @PrimaryKey
    val id: Int,
    val name: String,
)