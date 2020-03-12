package com.company.doria.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.company.doria.api.incident.IncidentRepository
import com.company.doria.api.incident_type.IncidentTypeRepository
import com.company.doria.db.persistence.incident.Incident
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject

class IncidentsWorker(
    private val ctx: Context,
    private val params: WorkerParameters,
    private val repo: IncidentRepository
) : Worker(ctx, params) {
    override fun doWork(): Result {
        return try {
            val call: Call<List<Incident>> = repo.getAllIncidentsSync()
            val response = call.execute()
            val incidents: List<Incident> = response.body()!!

            if (response.isSuccessful) {
                repo.deleteAll()
                repo.insert(incidents)
                Result.success()
            } else {
                Result.retry()
            }
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.retry()
        }
    }

    class Factory @Inject constructor(private val repo: IncidentRepository) : ChildWorkerFactory {
        override fun create(ctx: Context, params: WorkerParameters): ListenableWorker {
            return IncidentsWorker(
                ctx, params, repo
            )
        }
    }
    companion object {
        const val WORK_NAME = "com.numeral_iot.www.config_app.workers.IncidentsWorker"
    }
}

