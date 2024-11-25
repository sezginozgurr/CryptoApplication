package com.example.cryto.presentation.adapter.holder

interface AdapterClick {

    fun onClickStar(
        pairName: String,
        last: String,
        dailyPercent: String,
        pureDailyPercent: Double
    )

    fun onClickItem()
}