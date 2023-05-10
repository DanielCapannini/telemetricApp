package com.example.myapplication.data

import androidx.room.*
import java.util.*

@Entity(tableName = "instant")
data class Instant (
    @PrimaryKey
    val idInstant: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "is_session")
    var idParent: String,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double,

    @ColumnInfo(name = "acceleration_x")
    var accelerationX: Double,

    @ColumnInfo(name = "acceleration_y")
    var accelerationY: Double,

    @ColumnInfo(name = "acceleration_z")
    var accelerationZ: Double,

    @ColumnInfo(name = "datetime")
    var datetime: Date
    )

data class SessionWithInstant(
    @Embedded val session: Session,
    @Relation(parentColumn = "idSession",
              entityColumn = "idParent")
    val instants: List<Instant>
)