package com.tal.android.feedback.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nerdscorner.mvplib.events.fragment.BaseFragment
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.mvp.model.SentFeedbackModel
import com.tal.android.feedback.ui.mvp.presenter.SentFeedbackPresenter
import com.tal.android.feedback.ui.mvp.view.SentFeedbackView

class SentFeedbackFragment : BaseFragment<SentFeedbackPresenter>() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sent_feedback_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SentFeedbackPresenter(
                SentFeedbackView(this),
                SentFeedbackModel()
        )
    }
}
