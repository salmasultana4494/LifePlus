package com.example.test.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.models.RegistrationData


@Database(entities = [RegistrationData::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun appDAO() : AppDAO

}