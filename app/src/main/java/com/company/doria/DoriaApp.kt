package com.company.doria

import androidx.work.Configuration
import androidx.work.WorkManager
import com.company.doria.di.DaggerAppComponent
import com.company.doria.workers.CustomWorkerFactory
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject


class DoriaApp : DaggerApplication() {

    @Inject
    lateinit var workerFactory: CustomWorkerFactory

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        WorkManager.initialize(this, Configuration.Builder().setWorkerFactory(workerFactory).build())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}