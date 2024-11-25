package com.example.cryto.domain.usecase

import com.example.base.globalExt.buildDefaultFlow
import com.example.base.model.ApiResult
import com.example.cryto.domain.model.CryptoUiModel
import com.example.cryto.domain.repository.CryptoRepository
import com.example.base.qualifier.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCryptoUseCase @Inject constructor(
    private val repository: CryptoRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) {

    suspend fun getCrypto(): Flow<ApiResult<List<CryptoUiModel>>> = flow {
        val responseCrypto = repository.getCrypto()
        emit(responseCrypto)
    }.buildDefaultFlow(dispatcher)
}