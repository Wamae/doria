package com.company.doria.api.incident

import com.google.gson.annotations.SerializedName

class IncidentRequest(
    @SerializedName("incident_type_id")
    val incidentTypeId: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: String
)
