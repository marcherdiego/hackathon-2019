package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.Chapter
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.noCache
import com.tal.android.feedback.network.services.ChaptersService

class ChaptersListModel : BaseEventsModel() {
    private val chaptersService = ServiceGenerator.createServiceBundle(ChaptersService::class.java)

    fun fetchChapters() {
        chaptersService
            .noCache()
            .listChapters()
            .enqueueResponseNotNull(
                success = {
                    if (it.chapters == null) {
                        bus.post(ChaptersFetchFailedEvent())
                    } else {
                        bus.post(ChaptersFetchedSuccessfullyEvent(it.chapters))
                    }
                },
                fail = {
                    bus.post(ChaptersFetchFailedEvent(it.errorBody))
                }
            )
    }

    class ChaptersFetchedSuccessfullyEvent(val chapters: List<Chapter>)
    class ChaptersFetchFailedEvent(val error: String? = null)
}
