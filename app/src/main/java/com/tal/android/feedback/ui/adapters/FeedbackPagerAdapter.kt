package com.tal.android.feedback.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.tal.android.feedback.ui.fragments.ReceivedFeedbackFragment
import com.tal.android.feedback.ui.fragments.SentFeedbackFragment

class FeedbackPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.add(SentFeedbackFragment())
        fragments.add(ReceivedFeedbackFragment())
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "Sent"
        } else {
            "Received"
        }
    }
}