package com.example.cryto.domain.repository

import com.example.base.model.ApiResult
import com.example.cryto.domain.model.CryptoUiModel

interface CryptoRepository {

    suspend fun getCrypto(): ApiResult<List<String>>
}