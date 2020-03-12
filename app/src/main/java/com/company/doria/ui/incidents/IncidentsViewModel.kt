package com.company.doria.ui.incidents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.company.doria.api.Resource
import com.company.doria.api.incident.IncidentRepository
import com.company.doria.db.persistence.incident.Incident
import javax.inject.Inject

class IncidentsViewModel @Inject constructor(
    application: Application,
    private val repository: IncidentRepository
) :
    AndroidViewModel(application) {

    fun getAllJobsDb(): LiveData<List<Incident>?> {
        return repository.getAllIncidentsDb()
    }

    fun getAllJobs(): LiveData<Resource<List<Incident>>> {
        return repository.getAllJobs()
    }

}