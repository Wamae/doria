/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.company.doria.di.fragments

import com.company.doria.di.fragments.auth.AuthModule
import com.company.doria.di.fragments.auth.AuthViewModelsModule
import com.company.doria.di.fragments.create_incident.CreateIncidentViewModelsModule
import com.company.doria.di.fragments.incidents.IncidentsViewModelsModule
import com.company.doria.ui.auth.SignInFragment
import com.company.doria.di.scopes.FragmentScope
import com.company.doria.ui.create_incident.CreateIncidentFragment
import com.company.doria.ui.create_incident.CreateIncidentViewModel
import com.company.doria.ui.incidents.IncidentsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            AuthModule::class,
            AuthViewModelsModule::class]
    )
    abstract fun contributeSignInFragment(): SignInFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            IncidentsViewModelsModule::class]
    )
    abstract fun contributeIncidentsFragment(): IncidentsFragment

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            CreateIncidentViewModelsModule::class]
    )
    abstract fun contributeCreateIncidentFragment(): CreateIncidentFragment
}
