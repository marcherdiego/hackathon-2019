package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.ChapterDetailsActivity
import com.tal.android.feedback.ui.adapters.ChaptersAdapter
import com.tal.android.feedback.ui.mvp.model.ChaptersListModel
import com.tal.android.feedback.ui.mvp.view.ChaptersListView
import org.greenrobot.eventbus.Subscribe

class ChaptersListPresenter(view: ChaptersListView, model: ChaptersListModel) :
    BaseActivityPresenter<ChaptersListView, ChaptersListModel>(view, model) {

    @Subscribe
    fun onChapterFetchedSuccessfully(event: ChaptersListModel.ChaptersFetchedSuccessfullyEvent) {
        view.setChaptersAdapter(ChaptersAdapter(event.chapters, model.getBus()))
    }

    @Subscribe
    fun onChapterClicked(event: ChaptersAdapter.ChapterClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, ChapterDetailsActivity::class.java)
                    .putExtra(ChapterDetailsActivity.CHAPTER_ID, event.chapterId)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchChapters()
    }
}
