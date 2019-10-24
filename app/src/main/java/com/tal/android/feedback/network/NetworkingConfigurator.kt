package com.tal.android.feedback.network

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tal.android.configurators.Configurator
import com.tal.android.configurators.Configurator.Companion.AUTH_BASE_URL
import com.tal.android.configurators.Configurator.Companion.BASE_URL
import com.tal.android.configurators.Configurator.Companion.CONTEXT
import com.tal.android.configurators.Configurator.Companion.CUSTOM_HEADERS
import com.tal.android.configurators.Configurator.Companion.DEBUG_MODE
import com.tal.android.configurators.Configurator.Companion.NETWORKING_CONNECT_TIMEOUT_SECONDS
import com.tal.android.configurators.Configurator.Companion.NETWORKING_READ_TIMEOUT_SECONDS
import com.tal.android.configurators.Priority
import com.tal.android.feedback.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class NetworkingConfigurator : Configurator {
    override fun getPriority() = Priority.NONE

    override fun configure(config: Map<String, Any?>) {
        val context = config[CONTEXT] as Context
        val baseUrl = config[BASE_URL] as String
        val customHeaders = config[CUSTOM_HEADERS] as? List<Pair<String, String>>
        val authBaseUrl = config[AUTH_BASE_URL] as? String ?: ""
        val debugMode = config[DEBUG_MODE] as? Boolean ?: false


        val readTimeout = (config[NETWORKING_READ_TIMEOUT_SECONDS] as? Long)
            ?: BuildConfig.READ_TIMEOUT_SECONDS.toLong()
        val connectTimeout = (config[NETWORKING_CONNECT_TIMEOUT_SECONDS] as? Long)
            ?: BuildConfig.CONNECT_TIMEOUT_SECONDS.toLong()
        ServiceGenerator.httpPostClient = buildPostClient(
            readTimeout,
            connectTimeout,
            customHeaders,
            debugMode
        )
        ServiceGenerator.httpClient = buildNoCacheClient(
            readTimeout,
            connectTimeout,
            context,
            customHeaders,
            debugMode
        )
        ServiceGenerator.httpClientCache = buildCacheClient(
            readTimeout,
            connectTimeout,
            context,
            customHeaders,
            debugMode
        )
        ServiceGenerator.baseUrl = baseUrl
        ServiceGenerator.authBaseUrl = authBaseUrl
    }

    private fun buildPostClient(
        readTimeout: Long,
        connectTimeout: Long,
        customHeaders: List<Pair<String, String>>?,
        debugMode: Boolean
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                var request = chain.request()
                val requestBuilder = request.newBuilder()
                    .addHeader(ACCEPT_HEADER, APPLICATION_JSON_HEADER)
                    .removeHeader(AUTHORIZATION)
                request = requestBuilder.url(request.url()).build()
                chain.proceed(request)
            }
        if (customHeaders != null) {
            clientBuilder.addInterceptor(buildCustomHeadersInterceptor(customHeaders))
        }
        if (debugMode) {
            clientBuilder.addNetworkInterceptor(StethoInterceptor())
            clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return clientBuilder.build()
    }

    private fun buildNoCacheClient(
        readTimeout: Long,
        connectTimeout: Long,
        context: Context,
        customHeaders: List<Pair<String, String>>?,
        debugMode: Boolean
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .cache(NetworkUtils.buildCache(context))
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                var request = chain.request()
                val requestBuilder = request.newBuilder()
                    .addHeader(ACCEPT_HEADER, APPLICATION_JSON_HEADER)
                    .removeHeader(AUTHORIZATION)
                request = requestBuilder.url(request.url())
                    .header(CACHE_CONTROL_HEADER, "no-cache")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder().build()
                val response = chain.proceed(request)
                response.newBuilder()
                    .removeHeader(PRAGMA_HEADER)
                    .removeHeader(CACHE_CONTROL_HEADER)
                    .header(CACHE_CONTROL_HEADER, CACHE_CONTROL_VALUE)
                    .build()
            }
            .addNetworkInterceptor { chain ->
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder().build()
                val response = chain.proceed(request)
                response.newBuilder()
                    .removeHeader(PRAGMA_HEADER)
                    .removeHeader(CACHE_CONTROL_HEADER)
                    .header(CACHE_CONTROL_HEADER, CACHE_CONTROL_VALUE)
                    .build()
            }
        if (customHeaders != null) {
            clientBuilder.addInterceptor(buildCustomHeadersInterceptor(customHeaders))
        }
        if (debugMode) {
            clientBuilder.addNetworkInterceptor(StethoInterceptor())
            clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return clientBuilder.build()
    }

    private fun buildCacheClient(
        readTimeout: Long,
        connectTimeout: Long,
        context: Context,
        customHeaders: List<Pair<String, String>>?,
        debugMode: Boolean
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .cache(NetworkUtils.buildCache(context))
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                var request = chain.request()
                val requestBuilder = request.newBuilder()
                    .addHeader(ACCEPT_HEADER, APPLICATION_JSON_HEADER)
                    .removeHeader(AUTHORIZATION)
                request = requestBuilder.url(request.url()).build()
                chain.proceed(request)
            }
            .addInterceptor { chain ->
                val response = chain.proceed(
                    chain
                        .request()
                        .newBuilder()
                        .removeHeader(PRAGMA_HEADER)
                        .removeHeader(CACHE_CONTROL_HEADER)
                        .header(CACHE_CONTROL_HEADER, CACHE_CONTROL_OFFLINE_VALUE)
                        .build()
                )
                if (response.isSuccessful) {
                    response
                        .newBuilder()
                        .removeHeader(PRAGMA_HEADER)
                        .removeHeader(CACHE_CONTROL_HEADER)
                        .header(CACHE_CONTROL_HEADER, CACHE_CONTROL_VALUE)
                        .build()
                } else {
                    chain.proceed(
                        chain
                            .request()
                            .newBuilder()
                            .removeHeader(PRAGMA_HEADER)
                            .removeHeader(CACHE_CONTROL_HEADER)
                            .header(CACHE_CONTROL_HEADER, CACHE_CONTROL_VALUE)
                            .build()
                    )
                        .newBuilder()
                        .removeHeader(PRAGMA_HEADER)
                        .removeHeader(CACHE_CONTROL_HEADER)
                        .header(CACHE_CONTROL_HEADER, CACHE_CONTROL_VALUE)
                        .build()
                }
            }
            .addNetworkInterceptor { chain ->
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder().build()
                val response = chain.proceed(request)
                response.newBuilder()
                    .removeHeader(PRAGMA_HEADER)
                    .removeHeader(CACHE_CONTROL_HEADER)
                    .header(CACHE_CONTROL_HEADER, CACHE_CONTROL_VALUE)
                    .build()
            }
        if (customHeaders != null) {
            clientBuilder.addInterceptor(buildCustomHeadersInterceptor(customHeaders))
        }
        if (debugMode) {
            clientBuilder.addNetworkInterceptor(StethoInterceptor())
            clientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return clientBuilder.build()
    }

    private fun buildCustomHeadersInterceptor(customHeaders: List<Pair<String, String>>) =
        Interceptor { chain ->
            var request = chain.request()
            val requestBuilder = request.newBuilder()
            customHeaders.forEach {
                requestBuilder.addHeader(it.first, it.second)
            }
            request = requestBuilder
                .url(request.url())
                .build()
            chain.proceed(request)
        }

    companion object {
        private const val ACCEPT_HEADER = "Accept"
        private const val APPLICATION_JSON_HEADER =
            "application/json, application/vnd.tal-retail.v2"
        private const val AUTHORIZATION = "Authorization"
        private const val PRAGMA_HEADER = "Pragma"
        private const val CACHE_CONTROL_HEADER = "Cache-Control"
        private const val CACHE_CONTROL_VALUE = "public, max-age=2419200"
        private const val CACHE_CONTROL_OFFLINE_VALUE = "public, only-if-cached, max-stale=2419200"
    }
}
