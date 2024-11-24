package com.example.base.delegation


import android.content.Context
import androidx.fragment.app.Fragment
import com.example.base.model.ApiResult
import com.example.base.model.BaseResult
import com.app.core.base.model.HttpStatus
import com.app.core.base.model.LoadingType
import com.example.base.dialog.LoadingDialog
import com.example.base.globalExt.with
import com.example.base.model.ApiResponse
import com.example.base.model.MessageType
import com.example.base.model.NetworkState
import kotlinx.coroutines.delay
import java.io.IOException
import java.net.SocketTimeoutException

class HandleErrorDelegationImpl : HandleErrorDelegation {

    private var loadingDialog: LoadingDialog? = null

    override fun errorDelegation(
        fragment: Fragment,
        errorState: NetworkState.Error
    ) {
        val error = errorState.error
        val errorApiModel = error.errorApiModel
        val statusCode = error.statusCode

        if (isExistException(error.exception, errorState.retryCall)) return

        if (isHttpStatusUnauthorized(statusCode)) return

        if (isUnsafeConnection(statusCode)) return

        var errorMessage: String? = null

        //fixme all error case
        when (errorApiModel) {
            is ApiResponse<*> -> {
                errorMessage = if (errorApiModel.dataResult?.resultMessage.isNullOrEmpty().not()) {
                    errorApiModel.dataResult?.resultMessage.orEmpty()
                } else {
                    "GLOBAL ERROR MESSAGE"
                }
            }

            is BaseResult -> {
                errorMessage = errorApiModel.resultMessage
            }

            is ApiResult<*> -> {
                errorMessage =
                    error.errorApiModelType.name with errorState.error.exception.message.orEmpty()
            }

            else -> {
                errorMessage =
                    error.errorApiModelType.name with errorState.error.exception.message.orEmpty()
            }

        }

        //fixme special status code
        if (statusCode in 500..599) {
//            val error5xx = appResources.getLocalDataValue(AppConstants.TXT_API_ERROR_5XX)
//
//            errorMessage = error5xx?.replace("{p0}", "$statusCode")
//                ?: appResources.getLocalDataValue(AppConstants.TXT_APP_GENERAL_ERROR)
//                    .replace("{p0}", "$statusCode")
        }

        val message = errorMessage
            ?: "${(error.errorApiModel as BaseResult).resultMessage}"
        showErrorDelegation(
            fragment = fragment,
            errorType = errorState.messageType,
            message = message
        )
    }

    override fun showErrorDelegation(
        fragment: Fragment,
        errorType: MessageType,
        message: String?
    ) {

        when (errorType) {
            MessageType.NONE -> {
                //no*op
            }

            MessageType.SNACK_BAR -> Unit
            MessageType.DIALOG -> {
                /* ActionDialog.show(fragment.childFragmentManager, bundleOf(
                    ActionDialog.INFO_DIALOG_BUTTON_BLUE_KEY to "OK",
                    ActionDialog.INFO_DIALOG_STATE_UI_KEY to ActionDialog.ActionDialogState.ERROR.name,
                    ActionDialog.INFO_DIALOG_MESSAGE_KEY to message,
                    ActionDialog.INFO_DIALOG_TITLE_KEY to "ERROR",
                ), blueClickListener = {}, grayClickListener = {})
            */
            }

            MessageType.BOTTOM_SHEET -> {
                /*
                InfoBottomSheet.show(
                    fragment,
                    BottomSheetDialogType.ERROR,
                    bundleOf(
                        InfoBottomSheet.INFO_BOTTOM_TITLE_KEY to "ERROR",
                        InfoBottomSheet.INFO_BOTTOM_MESSAGE_KEY to message
                    )
                ) */
            }
        }
    }


    private fun isUnsafeConnection(resultCode: Int): Boolean {
        if (resultCode == HttpStatus.UNSAFE) {
            val resources = ""

            //fixme showDialog
//            infoDialog = InfoDialog(
//                context,
//               " resources?.getString(R.string.safe_connection_problem)",
//                {
//                    infoDialog?.hideDialog()
//                    infoDialog = null
//                    exitProcess(0)
//                },
//                "resources?.getString(R.string.info_dialog_close)",
//               " resources?.getString(R.string.info_dialog_title)"
//            ).apply { showDialog() }

            return true
        }

        return false
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
        if (isHttpStatusUnauthorized(statusCode)) return

        if (isUnsafeConnection(statusCode)) return

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
            MessageType.DIALOG -> {
                /*
                InfoBottomSheet.show(
                    fragment,
                    BottomSheetDialogType.SUCCESS,
                    bundleOf(InfoBottomSheet.INFO_BOTTOM_TITLE_KEY to message)
                ) */
            }

            MessageType.SNACK_BAR -> {
                /*
                InfoBottomSheet.show(
                    fragment,
                    BottomSheetDialogType.ERROR,
                    bundleOf(InfoBottomSheet.INFO_BOTTOM_TITLE_KEY to message)
                ) */
            }

            else -> {
                /*
                InfoBottomSheet.show(
                    fragment,
                    BottomSheetDialogType.SUCCESS,
                    bundleOf(InfoBottomSheet.INFO_BOTTOM_TITLE_KEY to message)
                )
            */
            }
        }
    }

    private fun isHttpStatusUnauthorized(statusCode: Int): Boolean {
        if (statusCode == HttpStatus.UNAUTHORIZED) {
            //  context?.let { safeContext ->
            //fixme logout and navigate login
//                appResources.logOut(safeContext)
//
//                safeContext.startActivity(
//                    Intent(
//                        safeContext,
//                        SplashActivity::class.java
//                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                )
            //         }

            return true
        }

        return false
    }

    private fun isExistException(exception: Throwable, retryCall: (() -> Unit)?): Boolean {
        return when (exception) {
            is IOException -> {
                isExistIOException(exception, retryCall)
            }

            else -> false
        }
    }

    private fun isExistIOException(exception: IOException, retryCall: (() -> Unit)?): Boolean {
        return when (exception) {
            is SocketTimeoutException -> {
                showSocketTimeoutMessage(retryCall)
                true
            }

            else -> {
                showNoInternetConnectionMessage(retryCall)
                true
            }
        }
    }

    private fun showSocketTimeoutMessage(retryCall: (() -> Unit)?) {
//        infoDialog?.hideDialog()
//        infoDialog = InfoDialog(
//            context,
//            appResources.getLocalDataValue(AppConstants.LBL_TIMEOUT),
//            { retryCall?.invoke() },
//            appResources.getLocalDataValue(AppConstants.TXT_TRY_AGAIN),
//            appResources.getLocalDataValue(AppConstants.LBL_ERROR)
//        ).apply { showDialog() }
    }

    private fun showNoInternetConnectionMessage(retryCall: (() -> Unit)?) {
//        infoDialog?.hideDialog()
//        infoDialog = InfoDialog(
//            context,
//            appResources.getLocalDataValue(AppConstants.TXT_APP_CHECK_INTERNET),
//            { retryCall?.invoke() },
//            appResources.getLocalDataValue(AppConstants.TXT_TRY_AGAIN),
//            appResources.getLocalDataValue(AppConstants.LBL_ERROR)
//        ).apply { showDialog() }
    }
}
