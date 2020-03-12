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

package com.company.doria.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.company.doria.BuildConfig
import com.company.doria.api.DoriaApiService
import com.google.gson.Gson
import com.company.doria.utilities.live_data.LiveDataCallAdapterFactory
import com.company.doria.api.HttpHeaderInterceptor
import com.company.doria.db.AppDatabase
import com.company.doria.db.persistence.incident.IncidentDao
import com.company.doria.db.persistence.incident_type.IncidentTypeDao
import com.company.doria.utilities.networking.ContextProviders
import com.company.doria.utilities.preferences.PreferenceHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceHelper.defaultPrefs(application as Context)
    }

    @Singleton
    @Provides
    fun provideKwadoService(sharedPreferences: SharedPreferences): DoriaApiService {

        val gson = Gson()

        val httpClient = getHttpClient(sharedPreferences)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory()) // Gets called first
            .build().create(DoriaApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpHeaderInterceptor(preferences: SharedPreferences): HttpHeaderInterceptor {
        return HttpHeaderInterceptor(preferences)
    }

    private fun getHttpClient(sharedPreferences: SharedPreferences): OkHttpClient.Builder {

        val headersInterceptor = HttpHeaderInterceptor(sharedPreferences)
        val loggingInterceptor = getHttpLoggingInterceptor()

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(headersInterceptor)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) httpClient.addInterceptor(loggingInterceptor)

        return httpClient
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Singleton
    @Provides
    fun provideCoroutineContext(): ContextProviders {
        return ContextProviders.getInstance()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "app_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideIncidentTypeDao(db: AppDatabase): IncidentTypeDao {
        return db.incidentTypeDao()
    }

    @Singleton
    @Provides
    fun provideIncidentDao(db: AppDatabase): IncidentDao {
        return db.incidentDao()
    }
}
