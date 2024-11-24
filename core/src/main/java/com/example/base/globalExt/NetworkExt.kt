package com.example.base.globalExt


import com.example.base.model.ApiResult
import com.example.base.model.BaseResult
import com.example.base.model.ErrorApiModelType
import com.app.core.base.model.HttpStatus
import com.example.base.model.ApiResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

inline val <T> Response<ApiResponse<T>>.asApiResult: ApiResult<T>
    get() {
        when {
            isSuccessful -> {
                return (body()?.let { safeBody ->
                    when{
                        safeBody.dataResult?.resultCode == 0->{
                            ApiResult.Success(
                                result = safeBody.data ?: safeBody.dataResult.resultMessage,
                                message = safeBody.dataResult.resultMessage.orEmpty(),
                                statusCode = this.code()
                            )
                        }
                        safeBody.dataResult?.resultCode != 0->{
                            ApiResult.Error(
                                errorApiModelType = ErrorApiModelType.API,
                                errorApiModel = BaseResult(safeBody.dataResult?.resultCode,safeBody.dataResult?.resultMessage),
                                statusCode = this.code()
                            )
                        }
                        else -> {
                            ApiResult.Failure(
                                exception = HttpException(this),
                                message =  safeBody.dataResult.resultMessage.orEmpty(),
                                payload = safeBody.payload,
                                statusCode = this.code()
                            )
                        }
                    }
                } ?: createErrorResult(this)) as ApiResult<T>
            }

            code() == HttpStatus.UNAUTHORIZED -> {
                return createErrorResult(this)
            }

            else -> {
                return createErrorResultWithErrorBody(this, this.errorBody())
            }
        }
    }

inline val <T> Response<T>.asSimpleApiResult: ApiResult<T>
    get() {
        when {
            this.isSuccessful -> {
                return body()?.let { body ->
                    ApiResult.Success(
                        result = body,
                        statusCode = this.code()
                    )
                } ?: createErrorResult(this)
            }

            code() == HttpStatus.UNAUTHORIZED -> {
                return createErrorResult(this)
            }

            else -> {
                return createErrorResultWithErrorBody(this, this.errorBody())
            }
        }
    }

fun <T> createErrorResultWithErrorBody(
    response: Response<T>,
    errorBody: ResponseBody?
): ApiResult.Error {
    errorBody?.let { body ->
        val errorBodyJsonString = body.string()

        runCatching {

            val errorApiResponseModel =
                Gson().fromJson(errorBodyJsonString, ApiResponse::class.java)

            if (errorApiResponseModel?.dataResult?.resultMessage != null) {
                return ApiResult.Error(
                    errorApiModel = errorApiResponseModel,
                    errorApiModelType = ErrorApiModelType.API,
                    statusCode = response.code()
                )
            }

        }
    }

    return createErrorResult(response)
}

fun <T> createRedirectionError(
    response: Response<T>
) = ApiResult.Failure(
    exception = HttpException(response),
    statusCode = response.code()
)

fun <T> createErrorResult(
    response: Response<T>
): ApiResult.Error {
    return ApiResult.Error(
        exception = HttpException(response),
        statusCode = response.code()
    )
}

