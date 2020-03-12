package com.company.doria.ui.create_incident

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.company.doria.R
import com.company.doria.api.Status
import com.company.doria.db.persistence.incident_type.IncidentType
import com.company.doria.ui.BaseActivity
import com.company.doria.viewmodels.CustomViewModelFactory
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.create_incident_fragment.*
import kotlinx.android.synthetic.main.sign_in_fragment.*
import timber.log.Timber
import javax.inject.Inject

class CreateIncidentFragment : DaggerFragment() {

    private lateinit var incidentTypesAdapter: ArrayAdapter<IncidentType>
    private lateinit var mContext: Context
    private lateinit var viewModel: CreateIncidentViewModel
    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_incident_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CreateIncidentViewModel::class.java)
        mContext = activity as Context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadIncidentTypes()
        initListener()
    }

    private fun initListener() {
        btn_report.setOnClickListener {

            val location = til_location.editText?.text.toString()
            val description = til_description.editText?.text.toString()
            Timber.d("Location: $location Description: $description")
            if (validateIndustryType() && validateLocation(location) && validateDescription(
                    description
                )
            ) {
                reportIncident(location, description)
            }
        }
    }

    private fun reportIncident(
        location: String,
        description: String
    ) {
        viewModel.reportIncident(location, description)
            .observe(viewLifecycleOwner, Observer { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        btn_report.showProgress {
                            buttonTextRes = R.string.reporting
                            progressColor = Color.WHITE
                        }
                        Timber.d("loading")
                    }
                    Status.ERROR -> {
                        Timber.d("error: ${resource?.message!!}")
                        btn_report.hideProgress(R.string.sign_in)
                        (activity as BaseActivity).showSnackBar(
                            resource.message,
                            Snackbar.LENGTH_LONG
                        )

                    }
                    else -> {
                        Timber.d("success created incident: ${resource.data}")
                        btn_report.hideProgress(R.string.report)
                        (activity as BaseActivity).showSnackBar(
                            "Success",
                            Snackbar.LENGTH_LONG
                        )
                    }
                }
            })
    }

    private fun validateIndustryType(): Boolean {
        if (viewModel.incidentTypeId.value == -1) {
            (activity as BaseActivity).showSnackBar(
                getString(R.string.select_incident_type),
                Snackbar.LENGTH_LONG
            )
            return false
        }
        return true
    }

    private fun validateLocation(location: String?): Boolean {
        if (TextUtils.isEmpty(location)) {
            til_location.error = getString(R.string.location_empty_error)
            return false
        } else {
            til_location.error = null
        }

        return true
    }

    private fun validateDescription(description: String?): Boolean {
        if (TextUtils.isEmpty(description)) {
            til_description.error = getString(R.string.description_empty_error)
            return false
        } else {
            til_description.error = null
        }

        return true
    }

    private fun loadIncidentTypes() {
        incidentTypesAdapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item)

        viewModel.incidentTypes.observe(viewLifecycleOwner, Observer { incidentType ->
            incidentTypesAdapter.addAll(incidentType!!)
        })

        sp_incident_type.setTitle(getString(R.string.select_incident_type).capitalize())
        sp_incident_type.setTitleGravity(Gravity.CENTER_HORIZONTAL)
        sp_incident_type.adapter = incidentTypesAdapter

        sp_incident_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedIncidentType = incidentTypesAdapter.getItem(position)
                viewModel.setIncidentType(selectedIncidentType?.id)
            }
        }
    }
}