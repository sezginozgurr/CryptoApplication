package com.example.crytoapplication.presentation.cryto

data class CrytoUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)