package com.example.cryto.presentation

import com.example.cryto.presentation.adapter.itemdata.CryptoDataItem
import com.example.cryto.presentation.adapter.itemdata.FavoritesDataItem

data class CryptoUiState(
    val cryptoList: List<CryptoDataItem> = emptyList(),
    val favoritesList: List<FavoritesDataItem> = emptyList(),
)