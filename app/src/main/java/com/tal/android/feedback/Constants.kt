package com.tal.android.feedback

object Constants {
    const val loggedUserId = 1

    enum class FeedbackTypes(val type: String) {
        QUALITY("Quality of Work"),
        QUANTITY("Quantity of Work"),
        DEPENDABILITY("Dependability"),
        PROFESSIONALISM("Professionalism")
    }
}