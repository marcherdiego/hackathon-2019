package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseFragmentPresenter
import com.tal.android.feedback.ui.adapters.FeedbackAdapter
import com.tal.android.feedback.ui.mvp.model.ReceivedFeedbackModel
import com.tal.android.feedback.ui.mvp.view.ReceivedFeedbackView
import org.greenrobot.eventbus.Subscribe

class ReceivedFeedbackPresenter(view: ReceivedFeedbackView, model: ReceivedFeedbackModel) :
    BaseFragmentPresenter<ReceivedFeedbackView, ReceivedFeedbackModel>(view, model) {

    @Subscribe
    fun onFeedbackFetchedSuccessfully(event: ReceivedFeedbackModel.FeedbackFetchedSuccessfullyEvent) {
        view.setFeedbackAdapter(
            FeedbackAdapter(
                FeedbackAdapter.TYPE_RECEIVED,
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
