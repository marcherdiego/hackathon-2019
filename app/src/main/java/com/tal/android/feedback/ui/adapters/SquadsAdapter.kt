package com.tal.android.feedback.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.bus.Bus
import com.tal.android.feedback.R
import com.tal.android.feedback.domain.Squad

class SquadsAdapter(private val squads: List<Squad>, private val bus: Bus) :
    RecyclerView.Adapter<SquadsAdapter.SquadsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadsViewHolder {
        return SquadsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.squad_item_row, null)
        )
    }

    override fun getItemCount() = squads.size

    override fun onBindViewHolder(holder: SquadsViewHolder, position: Int) {
        val squad = squads[position]
        holder.name.text = squad.name
        holder.productOwner.text = squad.productOwner?.getDisplayName()
        holder.scrumMaster.text = squad.scrumMaster?.getDisplayName()
        holder.description.text = squad.description
        holder.itemView.setOnClickListener {
            bus.post(SquadClickedEvent(squad.id))
        }
    }

    class SquadsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val productOwner: TextView = view.findViewById(R.id.product_owner)
        val scrumMaster: TextView = view.findViewById(R.id.scrum_master)
        val description: TextView = view.findViewById(R.id.description)
    }

    class SquadClickedEvent(val squadId: Int?)
}