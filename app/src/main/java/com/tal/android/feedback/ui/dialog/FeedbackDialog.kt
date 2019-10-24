package com.tal.android.feedback.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.RatingBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.tal.android.feedback.R
import com.tal.android.feedback.domain.Feedback
import com.tal.android.feedback.ui.adapters.FeedbackAdapter

object FeedbackDialog {
    fun show(context: Context, title: String, feedback: Feedback, type: String) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(title)
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.feedback_row_item, null)
        val owner = if (type == FeedbackAdapter.TYPE_SENT) {
            feedback.receiver?.getDisplayName()
        } else {
            feedback.sender?.getDisplayName()
        }
        view.findViewById<TextView>(R.id.type_label).text = type
        view.findViewById<TextView>(R.id.type_owner).text = owner
        view.findViewById<TextView>(R.id.feedback_text).text = feedback.text
        view.findViewById<RatingBar>(R.id.feedback_rating).rating = feedback.rating?.toFloat() ?: 0f

        val rootView = RelativeLayout(context)
        val padding = context.resources.getDimensionPixelSize(R.dimen.ui_spacing_8)
        rootView.setPadding(padding, padding, padding, padding)
        rootView.addView(view)
        alertDialog.setView(rootView)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Close") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}