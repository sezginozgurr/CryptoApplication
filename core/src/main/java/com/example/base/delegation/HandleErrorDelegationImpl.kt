package com.example.base.delegation


import android.content.Context
import androidx.fragment.app.Fragment
import com.example.base.model.ApiResult
import com.example.base.model.LoadingType
import com.example.base.dialog.LoadingDialog
import com.example.base.globalExt.with
import com.example.base.model.MessageType
import com.example.base.model.NetworkState
import kotlinx.coroutines.delay

class HandleErrorDelegationImpl : HandleErrorDelegation {

    private var loadingDialog: LoadingDialog? = null

    override fun errorDelegation(
        fragment: Fragment,
        errorState: NetworkState.Error
    ) {
        val error = errorState.error
        val errorApiModel = error.errorApiModel

        var errorMessage: String? = null

        errorMessage = when (errorApiModel) {
            is ApiResult<*> -> {
                error.errorApiModelType.name with errorState.error.exception.message.orEmpty()
            }

            else -> {
                error.errorApiModelType.name with errorState.error.exception.message.orEmpty()
            }
        }

        showErrorDelegation(
            fragment = fragment,
            errorType = errorState.messageType,
            message = errorMessage
        )
    }

    override fun showErrorDelegation(
        fragment: Fragment,
        errorType: MessageType,
        message: String?
    ) {

        when (errorType) {
            MessageType.NONE -> {}

            MessageType.DIALOG -> {}
        }
    }

    override suspend fun handleLoadingDelegation(
        context: Context,
        loadingType: LoadingType,
        text: String,
        delay: Long
    ) {
        when (loadingType) {
            LoadingType.NONE -> {
                loadingDialog?.hideDialog()
            }

            LoadingType.DEFAULT -> {
                if (loadingDialog?.isHidden?.not() == true) {
                    return
                }
                delay(delay)
                loadingDialog = LoadingDialog(context, text).apply {
                    showDialog()
                }
            }

            LoadingType.INEFFECTIVE -> {}

        }
    }

    override fun handleFailureDelegation(
        fragment: Fragment,
        statusCode: Int,
        message: String?,
        messageType: MessageType,
        payload: String?
    ) {

        val failureMessage = "failerMessage"

        showErrorDelegation(fragment, messageType, failureMessage)
    }

    override fun handleSuccessDelegation(
        fragment: Fragment,
        message: String,
        messageType: MessageType
    ) {
        when (messageType) {
            MessageType.NONE -> Unit
            MessageType.DIALOG -> {}
        }
    }
}
