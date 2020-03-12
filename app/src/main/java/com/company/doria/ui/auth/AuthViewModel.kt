package com.company.doria.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.company.doria.api.Resource
import com.company.doria.api.auth.LoginRequest
import com.company.doria.api.auth.LoginResponse
import com.company.doria.api.auth.AuthRepository
import javax.inject.Inject

class AuthViewModel @Inject constructor(application: Application,
                                        private val repository: AuthRepository
) :
    AndroidViewModel(application) {

    fun login(credentials: LoginRequest): LiveData<Resource<LoginResponse>> {
        return repository.login(credentials)
    }

}