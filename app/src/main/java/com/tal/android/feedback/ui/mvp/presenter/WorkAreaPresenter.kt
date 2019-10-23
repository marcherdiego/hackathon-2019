package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter

import com.tal.android.feedback.ui.mvp.model.WorkAreaModel
import com.tal.android.feedback.ui.mvp.view.WorkAreaView

class WorkAreaPresenter(view: WorkAreaView, model: WorkAreaModel) :
    BaseActivityPresenter<WorkAreaView, WorkAreaModel>(view, model)
