package com.tal.android.feedback.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.bus.Bus
import com.squareup.picasso.Picasso
import com.tal.android.feedback.R
import com.tal.android.feedback.domain.UserProfile
import com.tal.android.feedback.ui.picasso.CircleTransform

class UsersAdapter(private val users: List<UserProfile>, private val bus: Bus) :
    RecyclerView.Adapter<UsersAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item_row, null)
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = user.getDisplayName()
        holder.email.text = user.email
        holder.location.text = user.location
        holder.chapter.text = user.chapter?.name
        Picasso
            .get()
            .load(user.pictureUrl)
            .transform(CircleTransform())
            .into(holder.userImage)
        holder.itemView.setOnClickListener {
            bus.post(UserClickedEvent(user.id))
        }
    }

    class ChapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val email: TextView = view.findViewById(R.id.email)
        val location: TextView = view.findViewById(R.id.location)
        val chapter: TextView = view.findViewById(R.id.chapter)
        val userImage: ImageView = view.findViewById(R.id.user_image)
    }

    class UserClickedEvent(val userId: Int?)
}