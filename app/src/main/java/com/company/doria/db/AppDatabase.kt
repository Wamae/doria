package com.company.doria.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.doria.db.persistence.incident.Incident
import com.company.doria.db.persistence.incident.IncidentDao
import com.company.doria.db.persistence.incident_type.IncidentType
import com.company.doria.db.persistence.incident_type.IncidentTypeDao

//@TypeConverters(TechnichianTypeConverter::class)
@Database(
    entities = [
//        LoginResponse::class,
        IncidentType::class,
        Incident::class
    ], version = 3, exportSchema = false
)

//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun incidentTypeDao(): IncidentTypeDao
    abstract fun incidentDao(): IncidentDao

}