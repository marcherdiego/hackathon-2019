package com.tal.android.feedback.network.services

import com.tal.android.feedback.domain.Squad
import retrofit2.Call
import retrofit2.http.GET

interface SquadsService {
    @GET("/squads")
    fun listSquads(): Call<List<Squad>>
}
