package com.tal.android.feedback.network.services

import com.tal.android.feedback.domain.wrappers.AreasWrapper
import retrofit2.Call
import retrofit2.http.GET

interface AreasService {
    @GET("/areas")
    fun listAreas(): Call<AreasWrapper>
}
