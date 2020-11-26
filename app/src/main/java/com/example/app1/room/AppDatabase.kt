package com.example.app1.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app1.Highscore
import com.example.app1.Player

@Database(entities = arrayOf(Highscore::class), version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun highscoreDao(): HighscoreDao
}

@Database(entities = arrayOf(Player::class), version = 1)
abstract class AppDatabasePlayers : RoomDatabase(){
    abstract fun playerDao(): PlayerDao
}