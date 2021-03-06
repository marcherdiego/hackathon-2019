package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.Squad
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.noCache
import com.tal.android.feedback.network.services.SquadsService

class SquadsListModel : BaseEventsModel() {
    private val squadsService = ServiceGenerator.createServiceBundle(SquadsService::class.java)

    fun fetchSquads() {
        squadsService
            .noCache()
            .listSquads()
            .enqueueResponseNotNull(
                success = {
                    if (it.squads == null) {
                        bus.post(SquadsFetchFailedEvent())
                    } else {
                        bus.post(SquadsFetchedSuccessfullyEvent(it.squads))
                    }
                },
                fail = {
                    bus.post(SquadsFetchFailedEvent(it.errorBody))
                }
            )
    }

    class SquadsFetchedSuccessfullyEvent(val squads: List<Squad>)
    class SquadsFetchFailedEvent(val errorBody: String? = null)
}
