package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Session::class, Instant::class], version = 1, exportSchema = true)
abstract class Database : RoomDatabase(){
}