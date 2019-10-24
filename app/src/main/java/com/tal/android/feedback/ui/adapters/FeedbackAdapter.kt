package com.tal.android.feedback.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.bus.Bus
import com.tal.android.feedback.R
import com.tal.android.feedback.domain.Feedback

class FeedbackAdapter(
    private val type: String,
    private val feedback: List<Feedback>,
    private val bus: Bus
) : RecyclerView.Adapter<FeedbackAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.feedback_row_item, null)
        )
    }

    override fun getItemCount() = feedback.size

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val feedback = feedback[position]
        val owner = if (type == TYPE_SENT) {
            feedback.receiver?.getDisplayName()
        } else {
            feedback.sender?.getDisplayName()
        }
        holder.typeLabel.text = type
        holder.typeOwner.text = owner
        holder.feedbackText.text = feedback.text
        holder.feedbackRating.rating = feedback.rating?.toFloat() ?: 0f
        holder.itemView.setOnClickListener {
            bus.post(FeedbackClickedEvent(feedback))
        }
    }

    class ChapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val typeLabel: TextView = view.findViewById(R.id.type_label)
        val typeOwner: TextView = view.findViewById(R.id.type_owner)
        val feedbackText: TextView = view.findViewById(R.id.feedback_text)
        val feedbackRating: RatingBar = view.findViewById(R.id.feedback_rating)
    }

    class FeedbackClickedEvent(val feedback: Feedback)

    companion object {
        const val TYPE_SENT = "To"
        const val TYPE_RECEIVED = "From"
    }
}