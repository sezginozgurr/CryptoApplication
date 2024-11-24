package com.example.network.result.service

import retrofit2.Response
import retrofit2.http.GET

interface CryptoService {

    @GET(CryptoEndPoints.TICKER_CURRENCY)
    suspend fun getCrypto(): Response<List<String>>

}