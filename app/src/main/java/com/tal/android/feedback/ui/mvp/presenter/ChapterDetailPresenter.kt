package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter

import com.tal.android.feedback.ui.mvp.model.ChapterDetailModel
import com.tal.android.feedback.ui.mvp.view.ChapterDetailView

class ChapterDetailPresenter(view: ChapterDetailView, model: ChapterDetailModel) :
    BaseActivityPresenter<ChapterDetailView, ChapterDetailModel>(view, model)
