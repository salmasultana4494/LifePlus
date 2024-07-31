package com.example.test.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.test.models.RegistrationData

@Dao
interface AppDAO {

    @Insert
    suspend fun insertDataRegistration(registrationData: RegistrationData)

    @Query("SELECT * FROM user_table WHERE userName = :userName AND password = :password LIMIT 1")
    suspend fun findUserByCredentials(userName: String, password: String): RegistrationData?
}