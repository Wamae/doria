package com.company.doria.ui.incidents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.doria.R
import com.company.doria.db.persistence.incident.Incident
import timber.log.Timber


class IncidentsAdapter(private val clickListener: (Incident) -> Unit) :
    RecyclerView.Adapter<IncidentsAdapter.JobViewHolder>() {

    private var incidents: List<Incident>? = null
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        Timber.d("onCreateViewHolder")
        context = parent.context
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_incident, parent, false)
        return JobViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (incidents != null)
            incidents!!.size
        else
            0
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val incident = incidents!![position]
        Timber.d("onBindViewHolder ${incidents!!.size}")

        holder.tvIncidentType.text = incident.type
        holder.tvDesc.text = incident.description
        holder.tvCreatedAt.text = incident.createdAt
        holder.tvUpdatedAt.text = incident.updatedAt
        holder.tvStatus.text = incident.status

        holder.bindCLick(incident, clickListener)
    }

    internal fun setJobs(incidents: List<Incident>?) {
        this.incidents = incidents
        Timber.d("setIncidents ${incidents?.size}")
        notifyDataSetChanged()
    }

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIncidentType: TextView = itemView.findViewById(R.id.tv_incident_type)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_description)
        val tvCreatedAt: TextView = itemView.findViewById(R.id.tv_created_at)
        val tvUpdatedAt: TextView = itemView.findViewById(R.id.tv_updated_at)
        val tvStatus: TextView = itemView.findViewById(R.id.tv_status)

        fun bindCLick(incident: Incident, clickListener: (Incident) -> Unit) {
            itemView.setOnClickListener { _ ->
                clickListener(incident)
            }
        }
    }
}