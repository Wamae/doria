package com.company.doria.di.fragments.incidents

import androidx.lifecycle.ViewModel
import com.company.doria.ui.incidents.IncidentsViewModel
import com.company.doria.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class IncidentsViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(IncidentsViewModel::class)
    abstract fun bindIncidentsViewModel(viewModel: IncidentsViewModel): ViewModel

}