package com.example.crytoapplication.presentation.cryto

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CrytoViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableStateFlow(CrytoUiState())
    val uiState: StateFlow<CrytoUiState> = _uiState.asStateFlow()

}