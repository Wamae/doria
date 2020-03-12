package com.company.doria.api.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success") val success : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : TokenDetails
)