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
            LayoutInflater.from(parent.context)
                .inflate(
                    if (viewType == SINGLE_MEMBER_TYPE) {
                        R.layout.user_profile_reduced_single
                    } else {
                        R.layout.user_profile_reduced_double
                    },
                    null
                )
        )
    }

    override fun getItemCount() = (squadMembers.size - 1) / 2 + 2

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 || position == 1) {
            SINGLE_MEMBER_TYPE
        } else {
            DOUBLE_MEMBERS_TYPE
        }
    }

    override fun onBindViewHolder(holder: SquadsViewHolder, position: Int) {
        if (holder.itemViewType == SINGLE_MEMBER_TYPE) {
            val member = squadMembers[position]
            holder.role1.text = member.position
            holder.name1.text = member.getDisplayName()
            Picasso
                .get()
                .load(member.pictureUrl)
                .into(holder.image1)
        } else {
            val firstPosition = 2 * (position - 1)
            if (firstPosition < squadMembers.size) {
                val member = squadMembers[firstPosition]
                holder.role1.text = member.position
                holder.name1.text = member.getDisplayName()
                Picasso
                    .get()
                    .load(member.pictureUrl)
                    .into(holder.image1)

                val secondPosition = firstPosition + 1
                if (secondPosition < squadMembers.size) {
                    val member = squadMembers[secondPosition]
                    holder.role2?.text = member.position
                    holder.name2?.text = member.getDisplayName()
                    Picasso
                        .get()
                        .load(member.pictureUrl)
                        .into(holder.image2)
                }
            }
        }
    }

    class SquadsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val role1: TextView = view.findViewById(R.id.user_role_1)
        val name1: TextView = view.findViewById(R.id.user_name_1)
        val image1: ImageView = view.findViewById(R.id.user_image_1)

        val role2: TextView? = view.findViewById(R.id.user_role_2)
        val name2: TextView? = view.findViewById(R.id.user_name_2)
        val image2: ImageView? = view.findViewById(R.id.user_image_2)
    }

    companion object {
        private const val SINGLE_MEMBER_TYPE = 1
        private const val DOUBLE_MEMBERS_TYPE = 2
    }
}