package com.example.crytodetail

data class CryptoDetailUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)