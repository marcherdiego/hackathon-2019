package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.Constants
import com.tal.android.feedback.domain.Feedback
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.noCache
import com.tal.android.feedback.network.services.FeedbackService

class ReceivedFeedbackModel : BaseEventsModel() {
    private val feedbackService = ServiceGenerator.createServiceBundle(FeedbackService::class.java)

    fun fetchReceivedFeedback() {
        feedbackService
            .noCache()
            .getReceivedFeedback(Constants.loggedUserId)
            .enqueueResponseNotNull(
                success = {
                    if (it.feedbacks == null) {
                        bus.post(FeedbackFetchFailedEvent())
                    } else {
                        bus.post(FeedbackFetchedSuccessfullyEvent(it.feedbacks))
                    }
                },
                fail = {
                    bus.post(FeedbackFetchFailedEvent(it.errorBody))
                }
            )
    }

    class FeedbackFetchedSuccessfullyEvent(val feeedbacks: List<Feedback>)
    class FeedbackFetchFailedEvent(val errorBody: String? = null)
}
