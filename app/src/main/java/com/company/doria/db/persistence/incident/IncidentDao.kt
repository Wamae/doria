package com.company.doria.db.persistence.incident

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IncidentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(jobs: List<Incident>)

    @Query("DELETE FROM incidents")
    fun deleteAll()

    @Query("SELECT * from incidents")
    fun getAllIncidents(): LiveData<List<Incident>?>

}
