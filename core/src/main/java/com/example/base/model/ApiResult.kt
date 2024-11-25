package com.example.base.model


import com.app.core.base.model.HttpStatus
import com.google.gson.annotations.SerializedName

const val LOADING_STATUS = -1

sealed class ApiResult<out T>(
    @SerializedName("code")
    open val statusCode: Int,
    @SerializedName("message")
    open val message: String? = null
) {

    class Success<out T>(
        val result: T,
        override val message: String? = null,
        override val statusCode: Int = HttpStatus.SUCCESS
    ) : ApiResult<T>(statusCode, message)

    class Empty(
        override val statusCode: Int = HttpStatus.SUCCESS
    ) : ApiResult<Nothing>(statusCode)

    class Failure(
        val exception: Throwable = Exception(),
        val payload: String? = null,
        override val message: String? = null,
        override val statusCode: Int
    ) : ApiResult<Nothing>(statusCode, message)

    class Error(
        val exception: Throwable = Exception(),
        val errorApiModelType: ErrorApiModelType = ErrorApiModelType.UNKNOWN,
        val errorApiModel: Any? = null,
        override val statusCode: Int = HttpStatus.HTTP_400
    ) : ApiResult<Nothing>(statusCode)

    object Loading : ApiResult<Nothing>(LOADING_STATUS)
}

enum class ErrorApiModelType {
    UNKNOWN,
    API,
}

inline fun <T> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) action(result)
    return this
}

inline fun <T> ApiResult<T>.onEmpty(action: () -> Unit): ApiResult<T> {
    if (this is ApiResult.Empty) action()
    return this
}

/**
 *  API call is success but return fail from business.
 */
inline fun <T> ApiResult<T>.onFailure(action: (Throwable) -> Unit): ApiResult<T> {
    if (this is ApiResult.Failure) action(exception)
    return this
}

/**
 *  API call throws an error.
 */
inline fun <T> ApiResult<T>.onError(action: (ApiResult.Error) -> Unit): ApiResult<T> {
    if (this is ApiResult.Error) action(this)
    return this
}

inline fun <T> ApiResult<T>.onLoading(action: (T?) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) action(result)
    return this
}

inline fun <T> ApiResult<T>.orElse(action: () -> Unit): ApiResult<T> {
    if (this !is ApiResult.Success) action()
    return this
}

inline fun <T, R> ApiResult<T>.mapOnSuccess(map: (T?) -> R) = when (this) {
    is ApiResult.Success -> ApiResult.Success(
        map(result),
        message,
        this.statusCode
    )
    is ApiResult.Empty -> this
    is ApiResult.Failure -> this
    is ApiResult.Error -> this
    is ApiResult.Loading -> this
}

interface OnSuccess<T> {
    fun invoke(data: T)
}

interface OnFailure {
    fun invoke(exception: Throwable)
}

interface OnError {
    fun invoke(exception: ApiResult.Error)
}

interface OnLoading {
    fun invoke()
}
