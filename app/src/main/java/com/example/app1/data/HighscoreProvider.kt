package com.example.app1.data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.net.URI

class HighscoreProvider : ContentProvider() {

    lateinit var mDbHelper: HighscoresDbHelper

    val HIGHSCORES = 100
    val HIGHSCORE_ID = 101

    var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        sUriMatcher.addURI(HighscoreContract.CONTENT_AUTHORITY, HighscoreContract.PATH_HIGHSCORES, HIGHSCORES)
        sUriMatcher.addURI(HighscoreContract.CONTENT_AUTHORITY, HighscoreContract.PATH_HIGHSCORES + "/#", HIGHSCORE_ID)
    }

    override fun onCreate(): Boolean {
        mDbHelper = HighscoresDbHelper(context)

        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {


        val database = mDbHelper.readableDatabase

        var cursor: Cursor

        val match = sUriMatcher.match(uri)

        when(match){
            HIGHSCORES -> cursor = database.query(HighscoreContract.HighscoresEntry.TABLE_NAME,
                projection, selection, selectionArgs, null, null,sortOrder)

            HIGHSCORE_ID -> {
                val newSelection = HighscoreContract.HighscoresEntry._ID + "=?"
                val newSelectionArgs = arrayOf(ContentUris.parseId(uri).toString())

                cursor = database.query(HighscoreContract.HighscoresEntry.TABLE_NAME,
                    projection, newSelection, newSelectionArgs, null, null,sortOrder)
            }

            else -> throw IllegalArgumentException("Cannot query unknown URI " + uri)
        }

        return cursor
    }

    override fun getType(p0: Uri): String? {
        val match = sUriMatcher.match(p0)

        Log.e("HSProvider", "" + match)
        when(match){
            HIGHSCORES -> return HighscoreContract.HighscoresEntry.CONTENT_LIST_TYPE
            HIGHSCORE_ID -> return HighscoreContract.HighscoresEntry.CONTENT_ITEM_TYPE
            else -> throw IllegalStateException("Unknown URI " + p0 + " with match " + match)
        }
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        val match = sUriMatcher.match(p0)

        when(match){
            HIGHSCORES -> return insertHighscore(p0, p1)
            else -> throw IllegalArgumentException("Insertion is not supported for " + p0)
        }

    }

    fun insertHighscore(uri: Uri, contentValues: ContentValues?): Uri {
        val database = mDbHelper.writableDatabase

        val name = contentValues?.getAsString(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME)
            ?: throw IllegalArgumentException("Highscore requires a name")

        val score = contentValues.getAsString(HighscoreContract.HighscoresEntry.COLUMN_SCORE)
            ?: throw IllegalArgumentException("Highscore requires a name")

        val time = contentValues.getAsString(HighscoreContract.HighscoresEntry.COLUMN_TIME)
            ?: throw IllegalArgumentException("Highscore requires a name")

        val difficulty = contentValues.getAsString(HighscoreContract.HighscoresEntry.COLUMN_DIFFICULTY)
            ?: throw IllegalArgumentException("Highscore requires a name")


        val id = database.insert(HighscoreContract.HighscoresEntry.TABLE_NAME, null, contentValues)

        if(id == -1L){
            Log.e("HSProvider", "Failed to insert row for " + uri)
        }

        context?.contentResolver?.notifyChange(uri, null)

        return ContentUris.withAppendedId(uri, id)

    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        val database = mDbHelper.writableDatabase

        var rowsDeleted = 0
        val match = sUriMatcher.match(p0)
        when(match){
            HIGHSCORES -> rowsDeleted = database.delete(HighscoreContract.HighscoresEntry.TABLE_NAME, p1, p2)
            HIGHSCORE_ID ->{
                val newSelection = "_id=?"
                val newSelectionArgs = arrayOf(ContentUris.parseId(p0).toString())

                rowsDeleted = database.delete(HighscoreContract.HighscoresEntry.TABLE_NAME, newSelection, newSelectionArgs)
            }
        }
        if(rowsDeleted != 0){
            context?.contentResolver?.notifyChange(p0, null)
        }

        return rowsDeleted
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}