package com.example.base

import com.example.base.globalExt.asSimpleApiResult
import com.example.base.model.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseApiRepository {

    suspend fun <T> simpleRequest(
        call: suspend () -> Response<T>
    ): ApiResult<T> = withContext(Dispatchers.IO) {
        runCatching {
            call.invoke().asSimpleApiResult
        }.getOrElse { exception ->
            ApiResult.Error(exception)
        }
    }
}
