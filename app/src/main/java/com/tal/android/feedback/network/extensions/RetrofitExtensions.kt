package com.tal.android.feedback.network.extensions

import android.util.Log
import com.tal.android.feedback.network.ServiceBundle
import com.tal.android.feedback.network.exceptions.NetworkException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val X_ERROR_CODE = "X-ERROR-CODE"
private const val X_ERROR_MESSAGE = "X-ERROR-MESSAGE"

private const val TAG = "NETWORK CALL"

fun <T> Call<T>.enqueue(
        success: (T?) -> Unit = {},
        successCacheCheck: (Boolean, T?) -> Unit = { _, _ -> },
        fail: (NetworkException) -> Unit = {}
): Call<T> {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                val body = response.body()
                success(body)
                successCacheCheck(response.isFromCache(), body)
                Log.d(
                        TAG,
                        "${call.request().method()}: " +
                                "${call.request().url()} -> Response from " +
                                if (response.isFromCache()) {
                                    "Cache"
                                } else {
                                    "Network"
                                }
                )
            } else {
                fail(
                        NetworkException(response.message())
                                .setStatusCode(response.code())
                                .setErrorBody(response.errorBody()?.string())
                                .setErrorCode(response.headers()[X_ERROR_CODE])
                                .setErrorMessage(response.headers()[X_ERROR_MESSAGE])
                                .setResponse(response)
                )
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            if (call.isCanceled) {
                return
            }
            fail(
                    NetworkException(t.message)
                            .setThrowable(t)
            )
        }
    })
    return this
}

fun <T> Call<T>.enqueueResponseNotNull(
        success: (T) -> Unit = {},
        successCacheCheck: (Boolean, T) -> Unit = { _, _ -> },
        fail: (NetworkException) -> Unit = {}
): Call<T> {
    return enqueue(
            success = { body ->
                body?.let {
                    success(it)
                }
            },
            successCacheCheck = { fromCache, body ->
                body?.let {
                    successCacheCheck(fromCache, it)
                }
            },
            fail = fail
    )
}

fun Response<*>.isFromCache() = raw().networkResponse() == null

fun <T> ServiceBundle<T>.withCache(useCache: Boolean = true): T {
    return if (useCache) {
        cacheService
    } else {
        noCacheService
    }
}

fun <T> ServiceBundle<T>.post() = postService

fun <T> ServiceBundle<T>.delete() = postService

fun <T> ServiceBundle<T>.put() = postService

fun <T> ServiceBundle<T>.noCache() = postService
