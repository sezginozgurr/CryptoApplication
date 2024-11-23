package com.example.cryptoapplication.presentation.crytodetail

data class CrytoDetailUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)