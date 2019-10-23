package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.ntal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.UserProfileModel
import com.tal.android.feedback.ui.mvp.presenter.UserProfilePresenter
import com.tal.android.feedback.ui.mvp.view.UserProfileView

class UserProfileActivity : BaseActivity<UserProfilePresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_activity)

        presenter = UserProfilePresenter(
            UserProfileView(this),
            UserProfileModel()
        )
    }

    companion object {
        const val USER_PROFILE = "user_profile"
    }
}
