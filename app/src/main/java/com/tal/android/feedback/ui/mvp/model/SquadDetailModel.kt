package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.UserProfile
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.noCache
import com.tal.android.feedback.network.services.SquadsService

class SquadDetailModel(private val squadId: Int) : BaseEventsModel() {
    private val squadsService = ServiceGenerator.createServiceBundle(SquadsService::class.java)

    fun fetchSquadMembers() {
        squadsService
            .noCache()
            .getSquadById(squadId)
            .enqueueResponseNotNull(
                success = {
                    if (it.squad == null) {
                        bus.post(SquadFetchFailedEvent())
                    } else {
                        bus.post(SquadFetchedSuccessfullyEvent(
                            mutableListOf<UserProfile>().apply {
                                it.squad.productOwner?.let { productOwner ->
                                    productOwner.position = "Product Owner"
                                    add(productOwner)
                                }
                                it.squad.scrumMaster?.let { scrumMaster ->
                                    scrumMaster.position = "Scrum Master"
                                    add(scrumMaster)
                                }
                                it.squad.members?.let { members ->
                                    addAll(members)
                                }
                            }
                        ))
                    }
                },
                fail = {
                    bus.post(SquadFetchFailedEvent(it.errorBody))
                }
            )
    }

    class SquadFetchedSuccessfullyEvent(val squadMembers: List<UserProfile>)
    class SquadFetchFailedEvent(val error: String? = null)
}
