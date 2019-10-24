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
import com.tal.android.feedback.domain.Chapter
import com.tal.android.feedback.ui.picasso.CircleTransform

class ChaptersAdapter(private val chapters: List<Chapter>, private val bus: Bus) :
    RecyclerView.Adapter<ChaptersAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chapter_item_row, null)
        )
    }

    override fun getItemCount() = chapters.size

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapters[position]
        holder.name.text = chapter.name
        holder.chapterLead.text = chapter.leader?.getDisplayName()
        holder.membersCount.text = ((chapter.members?.size ?: 0) + 1).toString()
        if (chapter.description.isNullOrBlank()) {
            holder.description.visibility = View.GONE
        } else {
            holder.description.text = chapter.description
        }
        Picasso
            .get()
            .load(chapter.imageUrl)
            .transform(CircleTransform())
            .into(holder.chapterImage)
        holder.itemView.setOnClickListener {
            bus.post(ChapterClickedEvent(chapter.id))
        }
    }

    class ChapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val description: TextView = view.findViewById(R.id.description)
        val chapterLead: TextView = view.findViewById(R.id.chapter_lead)
        val membersCount: TextView = view.findViewById(R.id.members_count)
        val chapterImage: ImageView = view.findViewById(R.id.chapter_image)
    }

    class ChapterClickedEvent(val chapterId: Int?)
}