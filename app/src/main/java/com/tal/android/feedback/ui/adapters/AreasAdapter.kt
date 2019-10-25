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
import com.tal.android.feedback.domain.Area
import com.tal.android.feedback.ui.picasso.CircleTransform

class AreasAdapter(private val areas: List<Area>, private val bus: Bus) :
    RecyclerView.Adapter<AreasAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.area_item_row, null)
        )
    }

    override fun getItemCount() = areas.size

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val area = areas[position]
        holder.name.text = area.name
        Picasso
            .get()
            .load(area.imageUrl)
            .transform(CircleTransform())
            .into(holder.chapterImage)
        holder.itemView.setOnClickListener {
            bus.post(AreaClickedEvent(area.id))
        }
    }

    class ChapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val chapterImage: ImageView = view.findViewById(R.id.area_image)
    }

    class AreaClickedEvent(val areaId: Int?)
}