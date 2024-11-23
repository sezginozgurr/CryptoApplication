package com.example.cryptoapplication.data.repository

import com.example.cryptoapplication.data.source.local.CryptoRoomDB
import com.example.cryptoapplication.data.source.remote.MainService
import com.example.cryptoapplication.domain.repository.CryptoRepository
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val mainRoomDB: CryptoRoomDB,
) : CryptoRepository