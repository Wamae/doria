package com.company.doria.di.fragments.auth

import androidx.lifecycle.ViewModel
import com.company.doria.ui.auth.AuthViewModel
import com.company.doria.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

}