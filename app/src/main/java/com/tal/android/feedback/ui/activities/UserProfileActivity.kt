package com.tal.android.feedback.ui.activities

import android.os.Bundle
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.mvp.model.UserProfileModel
import com.tal.android.feedback.ui.mvp.presenter.UserProfilePresenter
import com.tal.android.feedback.ui.mvp.view.UserProfileView

class UserProfileActivity : BaseActivity<UserProfilePresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_activity)

        val userId = intent.getIntExtra(USER_ID, -1)
        if (userId == -1) {
            finish()
            return
        }
        presenter = UserProfilePresenter(
            UserProfileView(this),
            UserProfileModel(userId)
        )
    }

    companion object {
        const val USER_ID = "user_id"
    }
}
