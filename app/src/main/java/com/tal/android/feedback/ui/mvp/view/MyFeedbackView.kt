package com.tal.android.feedback.ui.mvp.view

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.FeedbackPagerAdapter

class MyFeedbackView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val tabLayout: TabLayout = activity.findViewById(R.id.tab_layout)
    private val viewPager: ViewPager = activity.findViewById(R.id.view_pager)

    fun setPagerAdapter(adapter: FeedbackPagerAdapter) {
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
