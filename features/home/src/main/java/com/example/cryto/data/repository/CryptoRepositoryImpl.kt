package com.example.cryto.data.repository

import com.example.base.BaseApiRepository
import com.example.base.model.ApiResult
import com.example.base.model.mapOnSuccess
import com.example.cryto.data.mapper.CryptoMapper
import com.example.cryto.data.service.CryptoService
import com.example.cryto.domain.model.CryptoUiModel
import com.example.cryto.domain.repository.CryptoRepository
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val service: CryptoService,
    private val mapper: CryptoMapper
) : CryptoRepository, BaseApiRepository() {

    override suspend fun getCrypto(): ApiResult<List<CryptoUiModel>> = simpleRequest {
        service.getCrypto()
    }.mapOnSuccess {
        mapper.mapToCrypto(it)
    }
}