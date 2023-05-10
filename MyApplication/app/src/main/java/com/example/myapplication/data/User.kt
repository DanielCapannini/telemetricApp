package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user")
data class User (
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "user_name")
    var username: String,

    @ColumnInfo(name = "user_emal")
    var usermail: String,

    @ColumnInfo(name = "user_passowrd")
    var password: String
)