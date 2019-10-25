package com.tal.android.feedback.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.nerdscorner.mvplib.events.bus.Bus
import com.tal.android.feedback.Constants
import com.tal.android.feedback.R
import com.tal.android.feedback.domain.FeedbackRequest
import com.tal.android.feedback.domain.UserProfile
import com.tal.android.feedback.domain.wrappers.FeedbackRequestWrapper
import com.tal.android.ui.core.widgets.TalButton

class SendFeedbackDialog(
    context: Context,
    private val receiver: UserProfile,
    bus: Bus
) {
    init {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle("Send feedback")
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.send_feedback_dialog, null)
        view.findViewById<TextView>(R.id.type_owner).text = receiver.getDisplayName()
        val ratingBar: RatingBar = view.findViewById(R.id.feedback_rating)
        val feedbackText: TextView = view.findViewById(R.id.feedback_text)
        val category: Spinner = view.findViewById(R.id.feedback_category)

        category.adapter = ArrayAdapter<String>(
            context,
            R.layout.feedback_category_spinner_item,
            arrayOf(
                Constants.FeedbackTypes.QUALITY.type,
                Constants.FeedbackTypes.QUANTITY.type,
                Constants.FeedbackTypes.DEPENDABILITY.type,
                Constants.FeedbackTypes.PROFESSIONALISM.type
            )
        )

        view.findViewById<TalButton>(R.id.send_feedback_button).setOnClickListener {
            bus.post(
                SendFeedbackClickedEvent(
                    FeedbackRequestWrapper().apply {
                        feedback = FeedbackRequest().apply {
                            senderId = Constants.loggedUserId
                            receiverId = receiver.id
                            text = feedbackText.text.toString()
                            rating = ratingBar.rating.toInt()
                            categoryId = category.selectedItemPosition
                        }
                    }
                )
            )
            alertDialog.dismiss()
        }

        val rootView = RelativeLayout(context)
        val padding = context.resources.getDimensionPixelSize(R.dimen.ui_spacing_8)
        rootView.setPadding(padding, padding, padding, padding)
        rootView.addView(view)
        alertDialog.setView(rootView)
        alertDialog.show()
    }

    class SendFeedbackClickedEvent(val feedback: FeedbackRequestWrapper)
}