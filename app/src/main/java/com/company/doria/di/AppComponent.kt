package com.company.doria.di

import android.app.Application
import com.company.doria.DoriaApp
import com.company.doria.di.fragments.FragmentBuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        /*ServiceBuildersModule::class,*/
        WorkerBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<DoriaApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}