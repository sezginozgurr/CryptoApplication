package com.example.base.model

import com.google.gson.annotations.SerializedName


data class BaseResult(
    @SerializedName("ResultCode")
    val resultCode: Int?,
    @SerializedName("ResultMessage")
    val resultMessage: String?,
)

