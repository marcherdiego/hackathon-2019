package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.Chapter
import com.tal.android.feedback.domain.UserProfile
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.noCache
import com.tal.android.feedback.network.services.ChaptersService

class ChapterDetailsModel(private val chapterId: Int) : BaseEventsModel() {
    private val chaptersService = ServiceGenerator.createServiceBundle(ChaptersService::class.java)

    fun fetchChapterInfo() {
        chaptersService
            .noCache()
            .getChapterById(chapterId)
            .enqueueResponseNotNull(
                success = {
                    if (it.chapter == null) {
                        bus.post(ChapterFetchFailedEvent())
                    } else {
                        bus.post(
                            ChapterFetchedSuccessfullyEvent(
                                mutableListOf<UserProfile>().apply {
                                    it.chapter.leader?.let { chapterLead ->
                                        chapterLead.position = "Chapter Lead"
                                        add(chapterLead)
                                    }
                                    it.chapter.members?.let { members ->
                                        addAll(members)
                                    }
                                },
                                it.chapter
                            )
                        )
                    }
                },
                fail = {
                    bus.post(ChapterFetchFailedEvent(it.errorBody))
                }
            )
    }

    class ChapterFetchedSuccessfullyEvent(val members: List<UserProfile>, val chapter: Chapter)
    class ChapterFetchFailedEvent(val error: String? = null)
}
