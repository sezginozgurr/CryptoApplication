package com.example.network.result.network

class BaseNetworkException(
    override var message: String?,
    override var code: Int?,
    override var priority: Int?,
) : INetworkError
