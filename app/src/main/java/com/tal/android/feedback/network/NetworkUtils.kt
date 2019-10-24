package com.tal.android.feedback.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Cache
import java.io.File

object NetworkUtils {

    private const val CACHE_NAME = "network_cache"
    private const val DEFAULT_CACHE_SIZE = (5 * 1024 * 1024).toLong() //5MB cache size
    private var cache: Cache? = null

    fun hasNetwork(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return connectivityManager
                ?.activeNetworkInfo
                ?.isConnected
                ?: false
    }

    fun clearCache() {
        val urls = cache?.urls()
        while (urls?.hasNext() == true) {
            urls.next()
            urls.remove()
        }
    }

    fun buildCache(context: Context, cacheSize: Long = DEFAULT_CACHE_SIZE): Cache {
        return cache ?: Cache(File(context.cacheDir, CACHE_NAME), cacheSize).apply {
            cache = this
        }
    }
}