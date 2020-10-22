package com.example.app1.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.BaseColumns

object HighscoreContract {

    val CONTENT_AUTHORITY = "com.example.app1"
    val BASE_CONTENT_URI: Uri = Uri.parse("content://" + CONTENT_AUTHORITY)
    val PATH_HIGHSCORES = "highscores"

     object HighscoresEntry : BaseColumns{

        val CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HIGHSCORES

        val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HIGHSCORES

        val CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HIGHSCORES)

        val TABLE_NAME = "highscores"

        val _ID = BaseColumns._ID
        val COLUMN_PLAYER_NAME = "name"
        val COLUMN_DIFFICULTY = "difficulty"
        val COLUMN_SCORE = "score"
        val COLUMN_TIME = "time"
    }

}