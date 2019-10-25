package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.Area
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.noCache
import com.tal.android.feedback.network.services.AreasService

class AreasListModel : BaseEventsModel() {
    private val areasService = ServiceGenerator.createServiceBundle(AreasService::class.java)

    fun fetchChapters() {
        areasService
            .noCache()
            .listAreas()
            .enqueueResponseNotNull(
                success = {
                    if (it.areas == null) {
                        bus.post(AreasFetchFailedEvent())
                    } else {
                        bus.post(AreasFetchedSuccessfullyEvent(it.areas))
                    }
                },
                fail = {
                    bus.post(AreasFetchFailedEvent(it.errorBody))
                }
            )
    }

    class AreasFetchedSuccessfullyEvent(val areas: List<Area>)
    class AreasFetchFailedEvent(val error: String? = null)
}
