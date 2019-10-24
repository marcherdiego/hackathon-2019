package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.MyFeedbackModel
import com.tal.android.feedback.ui.mvp.presenter.MyFeedbackPresenter
import com.tal.android.feedback.ui.mvp.view.MyFeedbackView

class MyFeedbackActivity : BaseActivity<MyFeedbackPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_feedback_activity)

        presenter = MyFeedbackPresenter(
                MyFeedbackView(this),
                MyFeedbackModel()
        )
    }
}
