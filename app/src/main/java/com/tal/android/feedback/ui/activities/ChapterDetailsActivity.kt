package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.ChapterDetailsModel
import com.tal.android.feedback.ui.mvp.presenter.ChapterDetailsPresenter
import com.tal.android.feedback.ui.mvp.view.ChapterDetailsView

class ChapterDetailsActivity : BaseActivity<ChapterDetailsPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chapter_detail_activity)

        val chapterId = intent.getIntExtra(CHAPTER_ID, -1)
        if (chapterId == -1) {
            finish()
            return
        }

        presenter = ChapterDetailsPresenter(
                ChapterDetailsView(this),
                ChapterDetailsModel(chapterId)
        )
    }

    companion object {
        const val CHAPTER_ID = "chapter_id"
    }
}
