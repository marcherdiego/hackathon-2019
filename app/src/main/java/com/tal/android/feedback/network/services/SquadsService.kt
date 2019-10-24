package com.tal.android.feedback.network.services

import com.tal.android.feedback.domain.wrappers.SquadWrapper
import com.tal.android.feedback.domain.wrappers.SquadsWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SquadsService {
    @GET("/squads")
    fun listSquads(): Call<SquadsWrapper>

    @GET("/squads/{id}")
    fun getSquadById(@Path("id") id: Int): Call<SquadWrapper>
}
