package com.example.cryto.presentation.listscreen.adapter.holder

interface AdapterClick {

    fun onClickStar(
        pairName: String,
        last: String,
        dailyPercent: String,
        pureDailyPercent: Double
    )

    fun onClickItem(
        pairName: String,
        last: Double,
        high: Double,
        low: Double,
        bid: Double,
        ask: Double,
        open: Double,
        volume: Double,
        average: Double
    )
}