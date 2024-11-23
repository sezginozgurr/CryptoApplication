package com.example.crytoapplication.data.repository

import com.example.crytoapplication.data.source.local.MainRoomDB
import com.example.crytoapplication.data.source.remote.MainService
import com.example.crytoapplication.domain.repository.CryptoRepository
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val mainService: MainService,
    private val mainRoomDB: MainRoomDB,
) : CryptoRepository