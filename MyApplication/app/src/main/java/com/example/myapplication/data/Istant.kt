package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "instant")
data class Instant (
    @PrimaryKey
    val idInstant: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "is_session")
    var idSession: String,

    @ColumnInfo(name = "latitude")
    var latitude: String,

    @ColumnInfo(name = "longitude")
    var longitude: String,

    @ColumnInfo(name = "acceleration_x")
    var accelerationX: String,

    @ColumnInfo(name = "acceleration_y")
    var accelerationY: String,

    @ColumnInfo(name = "acceleration_z")
    var accelerationZ: String,

    @ColumnInfo(name = "datetime")
    var datetime: Date
        )