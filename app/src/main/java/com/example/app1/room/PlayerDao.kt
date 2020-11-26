package com.example.app1.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.app1.Highscore
import com.example.app1.Player

@Dao
interface PlayerDao {

    @Insert
    fun insert(player: Player)

    @Delete
    fun delete(player: Player)

    @Query("SELECT * FROM player")
    fun getAll(): List<Player>

}