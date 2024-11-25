package com.example.base.globalExt

import com.example.base.model.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

inline fun <reified T> Flow<ApiResult<T>>.buildDefaultFlow(
    dispatcher: CoroutineDispatcher
): Flow<ApiResult<T>> {
    return this.onStart {
        emit(ApiResult.Loading)
    }.catch { e ->
        emit(ApiResult.Error(e))
    }.onCompletion {

    }.flowOn(dispatcher)
}