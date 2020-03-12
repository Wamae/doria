package com.company.doria.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.company.doria.api.incident_type.IncidentTypeRepository
import com.company.doria.db.persistence.incident_type.IncidentType
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject

class IncidentTypesWorker(
    private val ctx: Context,
    private val params: WorkerParameters,
    private val repo: IncidentTypeRepository
) : Worker(ctx, params) {
    override fun doWork(): Result {
        return try {
            val call: Call<List<IncidentType>> = repo.getIncidentTypesSync()
            val response = call.execute()
            val spaces: List<IncidentType> = response.body()!!

            if (response.isSuccessful) {
                repo.deleteAll()
                repo.insert(spaces)
                Result.success()
            } else {
                Result.retry()
            }
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.retry()
        }
    }

    class Factory @Inject constructor(private val repo: IncidentTypeRepository) : ChildWorkerFactory {
        override fun create(ctx: Context, params: WorkerParameters): ListenableWorker {
            return IncidentTypesWorker(
                ctx, params, repo
            )
        }
    }
    companion object {
        const val WORK_NAME = "com.numeral_iot.www.config_app.workers.IncidentTypesWorker"
    }
}

