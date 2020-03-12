package com.company.doria.api.auth

import com.google.gson.annotations.SerializedName

class TokenDetails(
    @SerializedName("token") val token: String
)
