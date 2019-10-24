package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseFragmentPresenter
import com.tal.android.feedback.ui.adapters.FeedbackAdapter
import com.tal.android.feedback.ui.mvp.model.SentFeedbackModel
import com.tal.android.feedback.ui.mvp.view.SentFeedbackView
import org.greenrobot.eventbus.Subscribe

class SentFeedbackPresenter(view: SentFeedbackView, model: SentFeedbackModel) :
    BaseFragmentPresenter<SentFeedbackView, SentFeedbackModel>(view, model) {

    @Subscribe
    fun onFeedbackFetchedSuccessfully(event: SentFeedbackModel.FeedbackFetchedSuccessfullyEvent) {
        view.setFeedbackAdapter(
            FeedbackAdapter(
                FeedbackAdapter.TYPE_SENT,
                event.feeedbacks,
                model.getBus()
            )
        )
    }

    @Subscribe
    fun onFeedbackClicked(event: FeedbackAdapter.FeedbackClickedEvent) {

    }

    override fun onResume() {
        super.onResume()
        model.fetchReceivedFeedback()
    }
}
