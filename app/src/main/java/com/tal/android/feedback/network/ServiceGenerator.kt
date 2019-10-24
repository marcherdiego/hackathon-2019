package com.tal.android.feedback.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    lateinit var httpPostClient: OkHttpClient
    lateinit var httpClient: OkHttpClient
    lateinit var httpClientCache: OkHttpClient
    lateinit var baseUrl: String
    lateinit var authBaseUrl: String

    fun <S> createServiceBundle(serviceClass: Class<S>): ServiceBundle<S> {
        return ServiceBundle(
            createServiceInternal(baseUrl, serviceClass, ServiceType.TYPE_POST),
            createServiceInternal(baseUrl, serviceClass, ServiceType.TYPE_NO_CACHE),
            createServiceInternal(baseUrl, serviceClass, ServiceType.TYPE_CACHE)
        )
    }

    fun <S> createAuthService(serviceClass: Class<S>) =
        createServiceInternal(authBaseUrl, serviceClass, ServiceType.TYPE_POST)

    private fun <S> createServiceInternal(
        baseUrl: String,
        serviceClass: Class<S>,
        serviceType: ServiceType
    ): S {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                when (serviceType) {
                    ServiceType.TYPE_POST -> httpPostClient
                    ServiceType.TYPE_CACHE -> httpClientCache
                    else -> httpClient
                }
            )
            .build()
            .create(serviceClass)
    }

    private enum class ServiceType {
        TYPE_POST,
        TYPE_CACHE,
        TYPE_NO_CACHE
    }

}
