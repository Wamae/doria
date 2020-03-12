package com.company.doria.api.auth

import com.google.gson.annotations.SerializedName

data class UserCred (
    @SerializedName("usernameOrEmail")
    val email: String,
    @SerializedName("password")
    val password: String)
