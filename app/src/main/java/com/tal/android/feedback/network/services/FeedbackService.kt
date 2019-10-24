package com.tal.android.feedback.network.services

import com.tal.android.feedback.domain.Feedback
import com.tal.android.feedback.domain.wrappers.FeedbackWrapper
import com.tal.android.feedback.domain.wrappers.FeedbacksWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FeedbackService {
    @POST("/feedbacks")
    fun sendFeedback(@Body feedback: Feedback): Call<Any>

    @GET("/feedbacks/{id}")
    fun getFeedbackById(@Path("id") id: Int): Call<FeedbackWrapper>

    @GET("/feedbacks/feedback_given/{id}")
    fun getGivenFeedback(@Path("id") id: Int): Call<FeedbacksWrapper>

    @GET("/feedbacks/feedback_received/{id}")
    fun getReceivedFeedback(@Path("id") id: Int): Call<FeedbacksWrapper>
}
