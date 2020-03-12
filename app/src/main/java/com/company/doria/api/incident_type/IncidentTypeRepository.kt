package com.company.doria.api.incident_type

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.company.doria.api.DoriaApiService
import com.company.doria.db.persistence.incident_type.IncidentType
import com.company.doria.db.persistence.incident_type.IncidentTypeDao
import com.company.doria.utilities.DoriaConstants
import com.company.doria.utilities.networking.ContextProviders
import com.company.doria.utilities.preferences.PreferenceHelper.get
import retrofit2.Call
import javax.inject.Inject

class IncidentTypeRepository @Inject constructor(
    private val preferences: SharedPreferences,
    private val dao: IncidentTypeDao,
    private val apiService: DoriaApiService,
    private val coroutineContext: ContextProviders
) {

/*    fun getUnAssignedTags(): LiveData<Resource<List<IncidentType>>> {
        return object : NetworkBoundResource<List<IncidentType>, List<IncidentType>>(coroutineContext) {
            override fun loadFromDb(): LiveData<List<IncidentType>?> {
                Timber.d("loadFromDb")
                return dao.getIncidentTypesDb()
            }

            override fun saveCallResult(tags: List<IncidentType>) {
                Timber.d("saveCallResult tags: ${tags.size}")
//                dao.deleteAll()
                dao.insert(tags)
            }

            override fun shouldFetch(data: List<IncidentType>?): Boolean {
                Timber.d("shouldFetch")
                return true
            }

            override fun createCall(): LiveData<ApiResponse<List<IncidentType>>> {
                Timber.d("createCall")
                return apiService.getUnAssignedTags()
            }

            override fun onFetchFailed() {
                super.onFetchFailed()
                Timber.d("onFetchFailed")
            }

        }.asLiveData()
    }*/

    fun insert(tags: List<IncidentType>) {
        dao.insert(tags)
    }

    fun getIncidentTypesSync(): Call<List<IncidentType>> {
//        val accessToken: String? = preferences[DoriaConstants.ACCESS_TOKEN]
        return apiService.getIncidentTypesSync()
    }

    fun getIncidentTypesDb(): LiveData<List<IncidentType>?> {
        return dao.getIncidentTypesDb()
    }

    fun deleteAll() {
        dao.deleteAll()
    }

}