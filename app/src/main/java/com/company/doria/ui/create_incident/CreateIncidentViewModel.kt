package com.company.doria.ui.create_incident

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.doria.api.Resource
import com.company.doria.api.incident.IncidentRepository
import com.company.doria.api.incident.IncidentRequest
import com.company.doria.api.incident_type.IncidentTypeRepository
import com.company.doria.db.persistence.incident.Incident
import com.company.doria.db.persistence.incident_type.IncidentType
import javax.inject.Inject

class CreateIncidentViewModel @Inject constructor(
    private val incidentTypeRepository: IncidentTypeRepository,
    private val incidentRepository: IncidentRepository
) :
    ViewModel() {
    private var _incidentTypeId: MutableLiveData<Int?> = MutableLiveData()
    val incidentTypeId: LiveData<Int?> get() = _incidentTypeId
    var location: MutableLiveData<String> = MutableLiveData()
    var description: MutableLiveData<String> = MutableLiveData()

    internal val incidentTypes: LiveData<List<IncidentType>?> =
        incidentTypeRepository.getIncidentTypesDb()

    init {
        _incidentTypeId.value = -1
        location.value = ""
        description.value = ""
    }

    fun setIncidentType(incidentTypeId: Int?) {
        this._incidentTypeId.value = incidentTypeId
    }

    fun reportIncident(location: String, description: String): LiveData<Resource<Incident>> {
        return incidentRepository.createIncident(
            IncidentRequest(
                incidentTypeId.value!!,
                description,
                location
            )
        )
    }
}