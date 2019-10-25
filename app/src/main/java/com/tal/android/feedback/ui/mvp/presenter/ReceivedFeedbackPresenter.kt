package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.bus.Bus
import com.nerdscorner.mvplib.events.presenter.BaseFragmentPresenter
import com.tal.android.feedback.ui.adapters.FeedbackAdapter
import com.tal.android.feedback.ui.dialog.ViewFeedbackDialog
import com.tal.android.feedback.ui.mvp.model.ReceivedFeedbackModel
import com.tal.android.feedback.ui.mvp.view.ReceivedFeedbackView
import org.greenrobot.eventbus.Subscribe

class ReceivedFeedbackPresenter(
    view: ReceivedFeedbackView,
    model: ReceivedFeedbackModel,
    bus: Bus
) :
    BaseFragmentPresenter<ReceivedFeedbackView, ReceivedFeedbackModel>(view, model, bus) {

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
        view.withActivity {
            ViewFeedbackDialog.show(
                this,
                "Feedback received",
                event.feedback,
                FeedbackAdapter.TYPE_RECEIVED
            )
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchReceivedFeedback()
    }
}
