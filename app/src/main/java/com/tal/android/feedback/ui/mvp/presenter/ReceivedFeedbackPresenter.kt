package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseFragmentPresenter
import com.tal.android.feedback.ui.mvp.view.ReceivedFeedbackView
import com.tal.android.feedback.ui.mvp.model.ReceivedFeedbackModel

class ReceivedFeedbackPresenter(view: ReceivedFeedbackView, model: ReceivedFeedbackModel) :
    BaseFragmentPresenter<ReceivedFeedbackView, ReceivedFeedbackModel>(view, model)
