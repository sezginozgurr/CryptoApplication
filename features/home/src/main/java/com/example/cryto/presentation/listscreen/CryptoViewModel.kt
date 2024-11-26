package com.example.cryto.presentation.listscreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.base.model.LoadingType
import com.example.base.CoreViewModel
import com.example.base.model.onSuccess
import com.example.cryto.data.model.CryptoEntityModel
import com.example.cryto.domain.usecase.CryptoDaoUseCase
import com.example.cryto.domain.usecase.GetCryptoUseCase
import com.example.cryto.presentation.listscreen.adapter.itemdata.CryptoDataItem
import com.example.cryto.presentation.listscreen.adapter.itemdata.FavoritesDataItem
import com.example.extension.collectIn
import com.example.home.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                    state.copy(cryptoList = uiModels.map {
                        val isFavorite = _uiState.value.favoritesList.any { favoriteItem ->
                            favoriteItem.pairName == it.pairNormalized
                        }

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
                            pureDailyPercent = it.pureDailyPercent,
                            denominatorSymbol = it.denominatorSymbol,
                            numeratorSymbol = it.numeratorSymbol,
                            order = it.order,
                            dailyPercentColor = it.dailyPercentColor,
                            isFavorite = isFavorite
                        )
                    })
                }
            }
        }
    }


    private fun addFavorites(
        pairName: String,
        last: String,
        dailyPercent: String,
        pureDailyPercent: Double
    ) = viewModelScope.launch {
        daoUseCase.insertFavorites(
            CryptoEntityModel(
                pairName = pairName,
                last = last,
                dailyPercent = dailyPercent,
                pureDailyPercent = pureDailyPercent
            )
        )
    }

    private fun deleteFavorites(
        id: Int,
        pairName: String,
        last: String,
        dailyPercent: String,
        pureDailyPercent: Double
    ) =
        viewModelScope.launch {
            daoUseCase.deleteFavorites(
                CryptoEntityModel(
                    id = id,
                    pairName = pairName,
                    last = last,
                    dailyPercent = dailyPercent,
                    pureDailyPercent = pureDailyPercent
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
                            dailyPercent = it.dailyPercent,
                            dailyPercentColor = if (it.pureDailyPercent < 0) R.color.drop_percent_color else R.color.rising_percent_color
                        )
                    })
                }
            }

        }
    }

    fun onClickStar(
        pairName: String,
        last: String,
        dailyPercent: String,
        pureDailyPercent: Double
    ) {
        val isAddedToFavorite = _uiState.value.favoritesList.any { it.pairName == pairName }
        val favoriteId = _uiState.value.favoritesList.firstOrNull { it.pairName == pairName }?.id

        if (!isAddedToFavorite) {
            addFavorites(
                pairName = pairName,
                last = last,
                dailyPercent = dailyPercent,
                pureDailyPercent = pureDailyPercent
            )
            return
        }

        deleteFavorites(
            id = favoriteId?.toInt() ?: -1,
            pairName = pairName,
            last = last,
            dailyPercent = dailyPercent,
            pureDailyPercent = pureDailyPercent
        )
    }


}