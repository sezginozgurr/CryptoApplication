package com.example.cryptoapplication.data.repository

import com.example.cryptoapplication.data.source.local.CryptoRoomDB
import com.example.cryptoapplication.data.source.remote.CryptoService
import com.example.cryptoapplication.domain.repository.CryptoRepository
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val cryptoService: CryptoService,
    private val mainRoomDB: CryptoRoomDB,
) : CryptoRepository