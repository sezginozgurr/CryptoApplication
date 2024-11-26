package com.example.cryto.presentation.listscreen

import com.example.cryto.presentation.listscreen.adapter.itemdata.CryptoDataItem
import com.example.cryto.presentation.listscreen.adapter.itemdata.FavoritesDataItem

data class CryptoUiState(
    val cryptoList: List<CryptoDataItem> = emptyList(),
    val favoritesList: List<FavoritesDataItem> = emptyList(),
)