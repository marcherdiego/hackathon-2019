package com.tal.android.feedback.network

class ServiceBundle<S>(
        internal val postService: S,
        internal val noCacheService: S,
        internal val cacheService: S
)