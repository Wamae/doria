package com.company.doria.db.persistence.incident_type

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "incident_types")
data class IncidentType(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) {
    override fun toString(): String {
        return name
    }
}