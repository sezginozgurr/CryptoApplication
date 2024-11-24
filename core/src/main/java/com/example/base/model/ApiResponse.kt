package com.example.base.model

import com.google.gson.annotations.SerializedName

class ApiResponse<out T>(
    @SerializedName("Result")
    val dataResult: BaseResult? = null,
    @SerializedName("ReturnObject")
    val data: T? = null,
    @SerializedName("TotalCount")
    val payload: String? = null
) {

    var isSuccess: Boolean = data != null
}
