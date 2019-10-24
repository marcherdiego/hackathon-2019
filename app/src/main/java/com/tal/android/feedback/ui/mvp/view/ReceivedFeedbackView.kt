package com.tal.android.feedback.ui.mvp.view

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.view.BaseFragmentView
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.FeedbackAdapter

class ReceivedFeedbackView(fragment: Fragment) : BaseFragmentView(fragment) {
    private val feedbackList: RecyclerView? = fragment.view?.findViewById(R.id.feedback_list)

    fun setFeedbackAdapter(adapter: FeedbackAdapter) {
        feedbackList?.adapter = adapter
    }
}
