package com.example.cryto.presentation

import androidx.lifecycle.viewModelScope
import com.app.core.base.model.LoadingType
import com.example.base.CoreViewModel
import com.example.base.model.onError
import com.example.base.model.onFailure
import com.example.base.model.onSuccess
import com.example.cryto.domain.usecase.GetCryptoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val useCase: GetCryptoUseCase
) : CoreViewModel() {

    private var _uiState = MutableStateFlow(CryptoUiState())
    val uiState: StateFlow<CryptoUiState> = _uiState.asStateFlow()

    fun getCrypto() {
        viewModelScope.launch {

            apiCall(loadingType = LoadingType.DEFAULT, call = {
                useCase.getCrypto()
            }).onError {
                //Todo custom error message
            }.onFailure {
                //Todo custom failure message
            }.onSuccess {
                _uiState.emit(CryptoUiState())
            }

        }
    }

}