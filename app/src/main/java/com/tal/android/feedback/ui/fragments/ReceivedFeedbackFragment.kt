package com.tal.android.feedback.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nerdscorner.mvplib.events.bus.Bus
import com.nerdscorner.mvplib.events.fragment.BaseFragment
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.mvp.model.ReceivedFeedbackModel
import com.tal.android.feedback.ui.mvp.presenter.ReceivedFeedbackPresenter
import com.tal.android.feedback.ui.mvp.view.ReceivedFeedbackView

class ReceivedFeedbackFragment : BaseFragment<ReceivedFeedbackPresenter>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.received_feedback_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = ReceivedFeedbackPresenter(
            ReceivedFeedbackView(this),
            ReceivedFeedbackModel(),
            Bus.newInstance
        )
    }
}
