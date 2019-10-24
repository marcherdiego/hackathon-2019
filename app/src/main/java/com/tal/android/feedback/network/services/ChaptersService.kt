package com.tal.android.feedback.network.services

import com.tal.android.feedback.domain.wrappers.ChapterWrapper
import com.tal.android.feedback.domain.wrappers.ChaptersWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChaptersService {
    @GET("/chapters")
    fun listChapters(): Call<ChaptersWrapper>

    @GET("/chapters/{id}")
    fun getChapterById(@Path("id") id: Int): Call<ChapterWrapper>
}
