package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.adapters.FeedbackPagerAdapter

import com.tal.android.feedback.ui.mvp.model.MyFeedbackModel
import com.tal.android.feedback.ui.mvp.view.MyFeedbackView

class MyFeedbackPresenter(view: MyFeedbackView, model: MyFeedbackModel) :
    BaseActivityPresenter<MyFeedbackView, MyFeedbackModel>(view, model) {
    init {
        view.fragmentManager?.let {
            view.setPagerAdapter(FeedbackPagerAdapter(it))
        }
    }
}
