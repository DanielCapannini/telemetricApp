package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Session::class, Instant::class], version = 1, exportSchema = true)
abstract class myDatabase : RoomDatabase(){

    abstract fun itemDAO(): databaseDAO

    companion object{
        @Volatile
        private var INSTANCE: myDatabase ?= null

        fun getDatabase(context: Context): myDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    myDatabase::class.java,
                    "items_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}