package com.example.cryto.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.base.model.LoadingType
import com.example.base.CoreViewModel
import com.example.base.model.onSuccess
import com.example.cryto.data.model.CryptoEntityModel
import com.example.cryto.domain.repository.FavoriteRepository
import com.example.cryto.domain.usecase.CryptoDaoUseCase
import com.example.cryto.domain.usecase.GetCryptoUseCase
import com.example.cryto.presentation.adapter.itemdata.CryptoDataItem
import com.example.cryto.presentation.adapter.itemdata.FavoritesDataItem
import com.example.extension.collectIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val useCase: GetCryptoUseCase,
    private val daoUseCase: CryptoDaoUseCase
) : CoreViewModel() {

    private var _uiState = MutableStateFlow(CryptoUiState())
    val uiState: StateFlow<CryptoUiState> = _uiState.asStateFlow()

    fun getCrypto() {
        viewModelScope.launch {
            apiCall(loadingType = LoadingType.DEFAULT, call = {
                useCase.getCrypto()
            }).onSuccess { uiModels ->
                Log.e("Response", "$uiModels")
                _uiState.update { state ->
                    state.copy(list = uiModels.map {
                        CryptoDataItem(
                            id = "1",
                            pair = it.pair,
                            pairNormalized = it.pairNormalized,
                            timestamp = it.timestamp,
                            last = it.last,
                            high = it.high,
                            low = it.low,
                            bid = it.bid,
                            ask = it.ask,
                            open = it.open,
                            volume = it.volume,
                            average = it.average,
                            daily = it.daily,
                            dailyPercent = it.dailyPercent,
                            denominatorSymbol = it.denominatorSymbol,
                            numeratorSymbol = it.numeratorSymbol,
                            order = it.order,
                            dailyPercentColor = it.dailyPercentColor
                        )
                    })
                }
            }

        }
    }


    fun addFavorites(pairName: String, last: String, dailyPercent: String) = viewModelScope.launch {
        daoUseCase.insertFavorites(
            CryptoEntityModel(
                pairName = pairName,
                last = last,
                dailyPercent = dailyPercent
            )
        )
    }

    fun deleteFavorites(pairName: String, last: String, dailyPercent: String) =
        viewModelScope.launch {
            daoUseCase.deleteFavorites(
                CryptoEntityModel(
                    pairName = pairName,
                    last = last,
                    dailyPercent = dailyPercent
                )
            )
        }

    fun getAllFavorites() {
        viewModelScope.launch {
            daoUseCase.getAllFavorites().collectIn(viewModelScope) { list ->
                _uiState.update { cryptoUiState ->
                    cryptoUiState.copy(favoritesList = list.map {
                        FavoritesDataItem(
                            id = it.id.toString(),
                            pairName = it.pairName,
                            last = it.last,
                            dailyPercent = it.dailyPercent
                        )
                    })
                }
            }

        }
    }


}