package com.tal.android.feedback.ui.mvp.view

import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.squareup.picasso.Picasso
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.SquadMembersAdapter

class SquadDetailView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val squadMembersList: RecyclerView = activity.findViewById(R.id.squad_members_list)
    private val squadImage: ImageView = activity.findViewById(R.id.squad_image)
    private val collapsingToolbar: CollapsingToolbarLayout = activity.findViewById(R.id.collapsing_toolbar)

    init {
        squadMembersList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun loadSquadDetails(squadName: String?, squadImageUrl: String?) {
        collapsingToolbar.title = squadName
        Picasso
            .get()
            .load(squadImageUrl)
            .into(squadImage)
    }

    fun setSquadMembersAdapter(adapter: SquadMembersAdapter) {
        squadMembersList.adapter = adapter
    }
}
