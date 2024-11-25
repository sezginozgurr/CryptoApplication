package com.example.crytodetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableStateFlow(CryptoDetailUiState())
    val uiState: StateFlow<CryptoDetailUiState> = _uiState.asStateFlow()

}