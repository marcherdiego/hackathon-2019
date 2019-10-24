package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseFragmentPresenter
import com.tal.android.feedback.ui.mvp.view.SentFeedbackView
import com.tal.android.feedback.ui.mvp.model.SentFeedbackModel

class SentFeedbackPresenter(view: SentFeedbackView, model: SentFeedbackModel) :
    BaseFragmentPresenter<SentFeedbackView, SentFeedbackModel>(view, model)
