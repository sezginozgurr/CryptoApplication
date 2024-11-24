package com.example.network.result.network

interface INetworkError {
    var message: String?
    var code: Int?
    var priority: Int?
}