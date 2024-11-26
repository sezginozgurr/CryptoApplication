package com.example.base.globalExt


import com.example.base.model.ApiResult
import com.example.base.model.ErrorApiModelType
import com.example.base.model.HttpStatus
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

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

            if (errorApiResponseModel?.message != null) {
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

fun <T> createErrorResult(
    response: Response<T>
): ApiResult.Error {
    return ApiResult.Error(
        exception = HttpException(response),
        statusCode = response.code()
    )
}

data class ApiResponse(
    val success: Boolean,
    val message: String,
    val code: Int
)

