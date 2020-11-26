package com.example.app1.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.app1.Highscore

@Dao
interface HighscoreDao {

    @Insert
    fun insert(highscore: Highscore)

    @Delete
    fun delete(highscore: Highscore)

    @Query("SELECT * FROM highscore")
    fun getAll(): List<Highscore>

    @Query("SELECT * FROM highscore WHERE difficulty LIKE :difficulty")
    fun findByDifficulty(difficulty: String): List<Highscore>

}