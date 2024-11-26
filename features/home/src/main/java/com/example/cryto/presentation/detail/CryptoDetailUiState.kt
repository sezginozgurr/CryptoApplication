package com.example.cryto.presentation.detail

data class CryptoDetailUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)