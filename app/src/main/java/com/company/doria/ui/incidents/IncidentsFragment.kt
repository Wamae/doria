package com.company.doria.ui.incidents

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.doria.R
import com.company.doria.db.persistence.incident.Incident
import com.company.doria.ui.BaseActivity
import com.company.doria.viewmodels.CustomViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_incidents.*
import javax.inject.Inject


class IncidentsFragment : DaggerFragment() {

    private lateinit var incidentsAdapter: IncidentsAdapter
    private lateinit var preferences: SharedPreferences
    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    lateinit var viewModel: IncidentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(IncidentsViewModel::class.java)
        preferences = (activity as BaseActivity).preferences
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_incidents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        incidentsAdapter = IncidentsAdapter { incident ->
            onKycTypeClick(incident)
        }
        rv_incidents.adapter = incidentsAdapter

        rv_incidents.layoutManager = LinearLayoutManager(context)

        getAllJobsDb()

        fab_report_incident.setOnClickListener {
                    findNavController().navigate(IncidentsFragmentDirections.actionIncidentsFragmentToCreateIncidentFragment())
        }
    }

    private fun onKycTypeClick(job: Incident) {
//        findNavController().navigate(JobsFragmentDirections.actionJobsFragmentToJobFragment(job))
    }

    private fun getAllJobsDb() {
        viewModel.getAllJobs().observe(viewLifecycleOwner, Observer { resource ->
            if (resource.data.isNullOrEmpty()) {
                iv_emoji.visibility = View.VISIBLE
                tv_message.visibility = View.VISIBLE
            } else {
                iv_emoji.visibility = View.GONE
                tv_message.visibility = View.GONE
                incidentsAdapter.setJobs(resource.data)
            }
        })
    }

}
