package com.tal.android.feedback.ui.mvp.view

import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.squareup.picasso.Picasso
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.ChapterMembersAdapter

class ChapterDetailsView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val chapterMembersList: RecyclerView = activity.findViewById(R.id.chapter_members_list)
    private val chapterImage: ImageView = activity.findViewById(R.id.chapter_image)
    private val collapsingToolbar: CollapsingToolbarLayout = activity.findViewById(R.id.collapsing_toolbar)

    init {
        chapterMembersList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun loadChapterDetails(chapterName: String?, chapterImageUrl: String?) {
        collapsingToolbar.title = chapterName
        Picasso
            .get()
            .load(chapterImageUrl)
            .into(chapterImage)
    }

    fun setChapterMembersAdapter(adapter: ChapterMembersAdapter) {
        chapterMembersList.adapter = adapter
    }
}
