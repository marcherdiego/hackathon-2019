package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.UserProfileActivity
import com.tal.android.feedback.ui.adapters.ChapterMembersAdapter

import com.tal.android.feedback.ui.mvp.model.ChapterDetailsModel
import com.tal.android.feedback.ui.mvp.view.ChapterDetailsView
import org.greenrobot.eventbus.Subscribe

class ChapterDetailsPresenter(view: ChapterDetailsView, model: ChapterDetailsModel) :
    BaseActivityPresenter<ChapterDetailsView, ChapterDetailsModel>(view, model) {

    @Subscribe
    fun onChapterFetchedSuccessfully(event: ChapterDetailsModel.ChapterFetchedSuccessfullyEvent) {
        with(event.chapter) {
            view.loadChapterDetails(
                "Chapter $name",
                imageUrl
            )
            view.setChapterMembersAdapter(ChapterMembersAdapter(event.members, model.getBus()))
        }
    }

    @Subscribe
    fun onChapterMemberClicked(event: ChapterMembersAdapter.ChapterMemberClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, UserProfileActivity::class.java)
                    .putExtra(UserProfileActivity.USER_ID, event.memberId)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchChapterInfo()
    }
}
