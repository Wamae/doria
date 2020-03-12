package com.company.doria.db.persistence.incident

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "incidents")
data class Incident(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("name") val type: String,
    @SerializedName("location") val location: String,
    @SerializedName("description") val description: String,
    @SerializedName("status") val status: String,
    @SerializedName("updated_at") val updatedAt: String
) : Parcelable