package com.tal.android.feedback.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tal.android.feedback.R
import com.tal.android.feedback.domain.UserProfile

class SquadMembersAdapter(private val squadMembers: List<UserProfile>) :
    RecyclerView.Adapter<SquadMembersAdapter.SquadsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadsViewHolder {
        return SquadsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_profile_reduced, null)
        )
    }

    override fun getItemCount() = squadMembers.size

    override fun onBindViewHolder(holder: SquadsViewHolder, position: Int) {
        val member = squadMembers[position]
        holder.role.text = member.position
        holder.name.text = member.getDisplayName()
        Picasso
            .get()
            .load(member.pictureUrl)
            .into(holder.image)
    }

    class SquadsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val role: TextView = view.findViewById(R.id.user_role)
        val name: TextView = view.findViewById(R.id.user_name)
        val image: ImageView = view.findViewById(R.id.user_image)
    }
}