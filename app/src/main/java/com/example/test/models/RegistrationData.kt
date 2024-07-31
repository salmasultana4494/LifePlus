package com.example.test.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class RegistrationData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0 ,
    @ColumnInfo(name = "name")val name: String ?,
    @ColumnInfo(name = "userName")val userName: String ?,
    @ColumnInfo(name = "password")val password: String ?,
    @ColumnInfo(name = "phoneNumber")val phoneNumber: String ?
): Parcelable
