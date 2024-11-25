package com.example.cryto.data.mapper


import com.example.cryto.data.model.CryptoResponse
import com.example.cryto.domain.model.CryptoUiModel
import com.example.home.R
import javax.inject.Inject

class CryptoMapper @Inject constructor() {

    fun mapToCrypto(it: CryptoResponse?) = it?.data?.map {
        CryptoUiModel(
            pair = it.pair,
            pairNormalized = it.pairNormalized.replace("_", "/"),
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
            dailyPercent = it.dailyPercent.toString().replace("-", ""),
            denominatorSymbol = it.denominatorSymbol,
            numeratorSymbol = it.numeratorSymbol,
            order = it.order,
            dailyPercentColor = if (it.dailyPercent < 0) R.color.red else R.color.green
        )
    }.orEmpty()

}