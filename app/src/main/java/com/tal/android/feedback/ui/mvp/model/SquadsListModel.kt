package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.Squad
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.withCache
import com.tal.android.feedback.network.services.SquadsService

class SquadsListModel : BaseEventsModel() {
    private val squadsService = ServiceGenerator.createServiceBundle(SquadsService::class.java)

    fun fetchSquads() {
        squadsService
            .withCache(true)
            .listSquads()
            .enqueueResponseNotNull(
                success = {
                    bus.post(SquadsFetchedSuccessfullyEvent(it))
                },
                fail = {
                    bus.post(SquadsFetchFailedEvent(it.errorBody))
                }
            )
    }

    class SquadsFetchedSuccessfullyEvent(val squads: List<Squad>)
    class SquadsFetchFailedEvent(val errorBody: String?)
}
