package com.example.network.result

import com.example.network.result.network.INetworkError
import com.example.network.result.type.LoadingType


sealed class NetworkState {
    data class Loading(
        val isLoading: Boolean = false,
        val forceStopLoading: Boolean = false,
        val loadingType: LoadingType = LoadingType.Default,
    ) : NetworkState()

    data class Failure(val error: INetworkError?) : NetworkState()

    data class Error(val error: INetworkError?) : NetworkState()

    data class Success(val args: Any) : NetworkState()
}
