package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity

import com.tal.android.feedback.ui.mvp.model.ChapterDetailModel
import com.tal.android.feedback.ui.mvp.presenter.ChapterDetailPresenter
import com.tal.android.feedback.ui.mvp.view.ChapterDetailView

class ChapterDetailActivity : BaseActivity<ChapterDetailPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.<<YOUR_LAYOUT_HERE>>)

        val chapterId = intent.getIntExtra(CHAPTER_ID, -1)
        if (chapterId == -1) {
            finish()
            return
        }
        presenter = ChapterDetailPresenter(
            ChapterDetailView(this),
            ChapterDetailModel()
        )
    }

    companion object {
        const val CHAPTER_ID = "chapter_id"
    }
}
