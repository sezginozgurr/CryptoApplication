package com.example.crytoapplication.presentation.crytodetail

data class CrytoDetailUiState(
    val isLoading: Boolean = false,
    val list: List<String> = emptyList(),
)