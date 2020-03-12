package com.company.doria.di.fragments.create_incident

import androidx.lifecycle.ViewModel
import com.company.doria.ui.incidents.IncidentsViewModel
import com.company.doria.di.ViewModelKey
import com.company.doria.ui.create_incident.CreateIncidentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class CreateIncidentViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreateIncidentViewModel::class)
    abstract fun bindCreateIncidentViewModel(viewModel: CreateIncidentViewModel): ViewModel

}