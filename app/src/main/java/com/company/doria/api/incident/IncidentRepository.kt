package com.company.doria.api.incident

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.company.doria.api.*
import com.company.doria.db.persistence.incident.Incident
import com.company.doria.db.persistence.incident.IncidentDao
import com.company.doria.utilities.DoriaConstants
import com.company.doria.utilities.networking.ContextProviders
import retrofit2.Call
import javax.inject.Inject
import com.company.doria.utilities.preferences.PreferenceHelper.get
import timber.log.Timber

class IncidentRepository @Inject constructor(
    private val preferences: SharedPreferences,
    private val dao: IncidentDao,
    private val apiService: DoriaApiService,
    private val coroutineContext: ContextProviders
) {

    fun getAllJobs(): LiveData<Resource<List<Incident>>> {
        return object :
            NetworkBoundResource<List<Incident>, List<Incident>>(coroutineContext) {
            override fun loadFromDb(): LiveData<List<Incident>?> {
                Timber.d("loadFromDb")
                return dao.getAllIncidents()
            }

            override fun saveCallResult(incidents: List<Incident>) {
                Timber.d("saveCallResult incidents: ${incidents.size}")
                dao.deleteAll()
                dao.insert(incidents)
            }

            override fun shouldFetch(data: List<Incident>?): Boolean {
                Timber.d("shouldFetch")
                return true
            }

            override fun createCall(): LiveData<ApiResponse<List<Incident>>> {
                Timber.d("createCall")
                return apiService.getIncidents()
            }

            override fun onFetchFailed() {
                super.onFetchFailed()
                Timber.d("onFetchFailed")
            }

        }.asLiveData()
    }

    fun getAllIncidentsDb(): LiveData<List<Incident>?> {
        return dao.getAllIncidents()
    }

    fun insert(incidents: List<Incident>) {
        dao.insert(incidents)
    }

    fun getAllIncidentsSync(): Call<List<Incident>> {
        return apiService.getIncidentsSync()
    }

    fun deleteAll() {
        dao.deleteAll()
    }

   fun createIncident(request: IncidentRequest): LiveData<Resource<Incident>> {
        return object :
            NetworkOnlyResource<Incident>() {

            override fun createCall(): LiveData<ApiResponse<Incident>> {
                Timber.d("createCall")
                return apiService.createIncident(request)
            }

        }.asLiveData()
    }
}