package com.company.doria.db.persistence.incident_type

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IncidentTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tags: List<IncidentType>)

    @Query("DELETE FROM incident_types")
    fun deleteAll()

    @Query("SELECT * from incident_types")
    fun getIncidentTypesDb(): LiveData<List<IncidentType>?>

}
