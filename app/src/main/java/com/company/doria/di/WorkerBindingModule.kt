package com.company.doria.di

import com.company.doria.workers.ChildWorkerFactory
import com.company.doria.workers.IncidentTypesWorker
import com.company.doria.di.workers.WorkerKey
import com.company.doria.workers.IncidentsWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
interface WorkerBindingModule {
    @Binds
    @IntoMap
    @WorkerKey(IncidentTypesWorker::class)
    fun bindIncidentTypesWorker(factory: IncidentTypesWorker.Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(IncidentsWorker::class)
    fun bindIncidentsWorker(factory: IncidentsWorker.Factory): ChildWorkerFactory
}