package com.company.doria.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.work.*
import com.company.doria.R
import com.company.doria.workers.IncidentTypesWorker
import com.company.doria.workers.IncidentsWorker
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    private lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workManager = WorkManager.getInstance(this)
    }

    fun downloadIncidentTypes() {
        val worker = IncidentTypesWorker::class.java
        val unassignedTagsWorkRequest = OneTimeWorkRequest.Builder(worker).setBackoffCriteria(
            BackoffPolicy.LINEAR,
            WorkRequest.MIN_BACKOFF_MILLIS,
            TimeUnit.SECONDS
        ).build()
        workManager.enqueue(unassignedTagsWorkRequest)

        workManager.getWorkInfoByIdLiveData(unassignedTagsWorkRequest.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Timber.d("WorkInfo.State.SUCCEEDED")
                    progress_bar.visibility = View.GONE
                    showSnackBar(R.string.incident_types_download_successful)
                } else if (workInfo != null && workInfo.state == WorkInfo.State.FAILED) {
                    Timber.d("WorkInfo.State.FAILED")
                    progress_bar.visibility = View.GONE
                    showSnackBar(R.string.incidents_type_download_failed)
                }
            })
    }

    fun downloadIncidents() {
        val worker = IncidentsWorker::class.java
        val unassignedTagsWorkRequest = OneTimeWorkRequest.Builder(worker).setBackoffCriteria(
            BackoffPolicy.LINEAR,
            WorkRequest.MIN_BACKOFF_MILLIS,
            TimeUnit.SECONDS
        ).build()
        workManager.enqueue(unassignedTagsWorkRequest)

        workManager.getWorkInfoByIdLiveData(unassignedTagsWorkRequest.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Timber.d("WorkInfo.State.SUCCEEDED")
                    progress_bar.visibility = View.GONE
                    showSnackBar(R.string.incidents_download_successful)
                } else if (workInfo != null && workInfo.state == WorkInfo.State.FAILED) {
                    Timber.d("WorkInfo.State.FAILED")
                    progress_bar.visibility = View.GONE
                    showSnackBar(R.string.incidents_download_failed)
                }
            })
    }
}