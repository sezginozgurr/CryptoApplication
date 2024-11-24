package com.example.cryto.presentation

import com.example.cryto.domain.model.CryptoUiModel

data class CryptoUiState(
    val list: List<CryptoUiModel> = emptyList(),
)