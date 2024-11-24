package com.example.base.delegation

import android.content.Context
import androidx.fragment.app.Fragment
import com.app.core.base.model.LoadingType
import com.example.base.model.MessageType
import com.example.base.model.NetworkState

interface HandleErrorDelegation {

    fun errorDelegation(
        fragment: Fragment,
        errorState: NetworkState.Error)

    fun showErrorDelegation(
        fragment: Fragment,
        errorType: MessageType,
        message: String?
    )

    suspend fun  handleLoadingDelegation(
        context: Context,
        loadingType: LoadingType,
        text: String,
        delay : Long = 0
    )

    fun handleFailureDelegation(
        fragment: Fragment,
        statusCode: Int,
        message: String?,
        messageType: MessageType,
        payload: String?
    )

    fun handleSuccessDelegation(
        fragment: Fragment,
        message: String,
        messageType: MessageType
    )
}
