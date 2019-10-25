package com.tal.android.feedback.ui.mvp.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.squareup.picasso.Picasso
import com.tal.android.feedback.R

class UserProfileView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val image = activity.findViewById<ImageView>(R.id.user_image)
    private val flag = activity.findViewById<ImageView>(R.id.flag_image)
    private val jobTitle = activity.findViewById<TextView>(R.id.job_title)
    private val chapter = activity.findViewById<TextView>(R.id.chapter)
    private val currentSquad = activity.findViewById<TextView>(R.id.squad)
    private val email = activity.findViewById<TextView>(R.id.email)
    private val location = activity.findViewById<TextView>(R.id.location)
    private val timeZone = activity.findViewById<TextView>(R.id.timezone)
    private val localTime = activity.findViewById<TextView>(R.id.local_time)
    private val userLeader = activity.findViewById<TextView>(R.id.leader)
    private val slackUser = activity.findViewById<TextView>(R.id.slack)
    private val collapsingToolbar: CollapsingToolbarLayout = activity.findViewById(R.id.collapsing_toolbar)
    private val actionButton = activity.findViewById<FloatingActionButton>(R.id.fab)

    init {
        actionButton.setOnClickListener {
            bus.post(UserActionButtonButtonClickedEvent())
        }
        activity.findViewById<View>(R.id.chapter_container).setOnClickListener {
            bus.post(UserChapterClickedEvent())
        }
        activity.findViewById<View>(R.id.squad_container).setOnClickListener {
            bus.post(UserSquadClickedEvent())
        }
        activity.findViewById<View>(R.id.email_container).setOnClickListener {
            bus.post(UserEmailClickedEvent())
        }
        activity.findViewById<View>(R.id.leader_container).setOnClickListener {
            bus.post(UserLeaderClickedEvent())
        }
        activity.findViewById<View>(R.id.slack_container).setOnClickListener {
            bus.post(UserSlackClickedEvent())
        }
        activity.findViewById<View>(R.id.feedbacks_button).setOnClickListener {
            bus.post(ViewFeedbacksClickedEvent())
        }
    }

    fun hideFab() {
        actionButton.hide()
    }

    fun showFab() {
        actionButton.show()
    }

    fun loadUserData(
        email: String?,
        displayName: String?,
        position: String?,
        pictureUrl: String?,
        flagUrl: String?,
        location: String?,
        timeZone: String?,
        localTime: String?,
        currentSquad: String?,
        chapter: String?,
        userLeader: String?,
        slackName: String?
    ) {
        this.collapsingToolbar.title = displayName
        this.jobTitle.text = position
        this.email.text = email
        this.location.text = location
        this.timeZone.text = timeZone
        this.localTime.text = localTime
        this.currentSquad.text = currentSquad
        this.chapter.text = chapter
        this.userLeader.text = userLeader
        this.slackUser.text = slackName
        Picasso
            .get()
            .load(pictureUrl)
            .into(image)
        Picasso
            .get()
            .load(flagUrl)
            .into(flag)
    }

    class UserActionButtonButtonClickedEvent
    class UserChapterClickedEvent
    class UserSquadClickedEvent
    class UserEmailClickedEvent
    class UserLeaderClickedEvent
    class UserSlackClickedEvent
    class ViewFeedbacksClickedEvent
}
