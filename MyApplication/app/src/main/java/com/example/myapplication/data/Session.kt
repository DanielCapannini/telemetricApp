package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "session")
data class Session (
    @PrimaryKey
    val idSession: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "id_user")
    var idUser: String,

    @ColumnInfo(name = "name")
    var nome: String,

    @ColumnInfo(name = "date")
    var date: Date
        )