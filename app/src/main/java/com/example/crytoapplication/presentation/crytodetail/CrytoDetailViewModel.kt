package com.example.crytoapplication.presentation.crytodetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CrytoDetailViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableStateFlow(CrytoDetailUiState())
    val uiState: StateFlow<CrytoDetailUiState> = _uiState.asStateFlow()

}