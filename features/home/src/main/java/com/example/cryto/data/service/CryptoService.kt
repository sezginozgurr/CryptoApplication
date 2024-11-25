package com.example.cryto.data.service

import com.example.cryto.data.model.CryptoResponse
import retrofit2.Response
import retrofit2.http.GET


interface CryptoService {

    @GET(CryptoEndPoints.TICKER_CURRENCY)
    suspend fun getCrypto(): Response<CryptoResponse>

}