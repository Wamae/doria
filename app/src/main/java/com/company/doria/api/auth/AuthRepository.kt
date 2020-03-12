package com.company.doria.api.auth

import androidx.lifecycle.LiveData
import com.company.doria.api.Resource
import com.company.doria.api.DoriaApiService
import com.company.doria.api.NetworkOnlyResource
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: DoriaApiService) {

    fun login(credentials: LoginRequest): LiveData<Resource<LoginResponse>> {
        return object : NetworkOnlyResource<LoginResponse>() {
            override fun createCall() = apiService.login(credentials)

        }.asLiveData()
    }

}