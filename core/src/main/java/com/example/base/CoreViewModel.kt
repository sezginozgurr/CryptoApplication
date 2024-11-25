package com.example.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.model.ApiResult
import com.example.base.model.LoadingType
import com.example.base.model.MessageType
import com.example.base.model.NetworkState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

abstract class CoreViewModel : ViewModel() {

    private val _showLoading = MutableStateFlow(false)
    val showLoading = _showLoading.asSharedFlow()

    private val stateFlow: MutableSharedFlow<NetworkState> = MutableSharedFlow()
    private val _networkStateFlow = MutableSharedFlow<NetworkState>()

    val networkStateFlow: SharedFlow<NetworkState>
        get() = _networkStateFlow.asSharedFlow()

    var loadingCount = 0

    suspend fun <T : Any> apiCall(
        loadingType: LoadingType,
        errorMessageType: MessageType = MessageType.NONE,
        successMessageType: MessageType = MessageType.NONE,
        retryCall: (() -> Unit)? = null,
        call: suspend () -> Flow<ApiResult<T>>
    ): ApiResult<T> = suspendCoroutine { continuation ->
        viewModelScope.launch {
            call.invoke().collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Loading -> {
                        if (loadingType != LoadingType.NONE)
                            manageLoadingState(apiResult).also {
                                if (manageLoading() != LoadingType.INEFFECTIVE)
                                _networkStateFlow.emit(NetworkState.Loading(manageLoading()))
                            }
                    }

                    is ApiResult.Success -> {

                        manageLoadingState(apiResult)
                        _networkStateFlow.emit(NetworkState.Success(apiResult, successMessageType))
                        continuation.resume(apiResult)
                    }

                    is ApiResult.Error -> {
                        manageLoadingState(apiResult)
                        handleApiError(apiResult, errorMessageType, retryCall)
                        continuation.resume(apiResult)
                    }

                    is ApiResult.Failure -> {
                        manageLoadingState(apiResult)
                        ApiResult.Error(exception = Exception(apiResult.message))
                        continuation.resume(apiResult)
                    }

                    else -> {
                        //no*op
                    }
                }

            }
        }
    }

    private suspend fun <T : Any> handleApiError(
        response: ApiResult<T>,
        errorType: MessageType,
        retryCall: (() -> Unit)?
    ) {
        _networkStateFlow.emit(NetworkState.Loading(LoadingType.NONE))

        when (response) {
            is ApiResult.Error -> {
                _networkStateFlow.emit(NetworkState.Error(response, errorType, retryCall))
            }

            is ApiResult.Failure -> {
                _networkStateFlow.emit(NetworkState.Failure(response, errorType))
            }

            else -> {
                //no-op
            }
        }
    }

    private fun manageLoadingState(result: ApiResult<Any>) {
        when (result) {
            ApiResult.Loading -> loadingCount++
            else -> loadingCount--


        }
        Log.e("ManagingLoading", "$loadingCount")
        viewModelScope.launch {
            if (manageLoading() != LoadingType.INEFFECTIVE)
                _networkStateFlow.emit(NetworkState.Loading(manageLoading()))
        }

    }

    private fun manageLoading(): LoadingType {
        return when {
            loadingCount > 1 -> LoadingType.INEFFECTIVE
            loadingCount == 1 -> LoadingType.DEFAULT
            loadingCount == 0 -> LoadingType.NONE
            else -> LoadingType.INEFFECTIVE
        }
    }

}
