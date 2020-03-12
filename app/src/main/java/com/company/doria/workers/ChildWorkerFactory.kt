package com.company.doria.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(ctx: Context, params: WorkerParameters): ListenableWorker
}
