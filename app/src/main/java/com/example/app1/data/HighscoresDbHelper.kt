package com.example.app1.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class HighscoresDbHelper(context: Context?) : SQLiteOpenHelper(context, "highscores.db", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {

        val SQL_CREATE_ENTRIES  =
            "CREATE TABLE " +  HighscoreContract.HighscoresEntry.TABLE_NAME + " (" +
                    HighscoreContract.HighscoresEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME + " TEXT NOT NULL, " +
                    HighscoreContract.HighscoresEntry.COLUMN_SCORE + " INTEGER NOT NULL, " +
                    HighscoreContract.HighscoresEntry.COLUMN_TIME + " REAL NOT NULL, " +
                    HighscoreContract.HighscoresEntry.COLUMN_DIFFICULTY + " TEXT NOT NULL);"

        val SQL_CREATE_ENTRIES_PLAYER =
            "CREATE TABLE " + HighscoreContract.HighscoresEntry.TABLE_NAME_PLAYERS + " (" +
                    HighscoreContract.HighscoresEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME + " TEXT NOT NULL);"

        p0?.execSQL(SQL_CREATE_ENTRIES)
        p0?.execSQL(SQL_CREATE_ENTRIES_PLAYER)

        Log.e("dbhelper","create")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS " + HighscoreContract.HighscoresEntry.TABLE_NAME)
        p0?.execSQL("DROP TABLE IF EXISTS " + HighscoreContract.HighscoresEntry.TABLE_NAME_PLAYERS)
        onCreate(p0)
    }
}