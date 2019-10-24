package com.tal.android.feedback.network.services

import com.tal.android.feedback.domain.wrappers.UserWrapper
import com.tal.android.feedback.domain.wrappers.UsersWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersService {
    @GET("/users")
    fun listUsers(): Call<UsersWrapper>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<UserWrapper>
}
