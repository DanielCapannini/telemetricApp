package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface databaseDAO {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSession(session: Session)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInstant(instant: Instant)
}