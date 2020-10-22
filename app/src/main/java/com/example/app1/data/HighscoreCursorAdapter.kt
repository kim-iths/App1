package com.example.app1.data

import android.content.Context
import android.database.Cursor
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import androidx.core.content.contentValuesOf
import com.example.app1.R

class HighscoreCursorAdapter(val context: Context, cursor: Cursor?) : CursorAdapter(context, cursor, 0) {

    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.list_item_highscore, p2, false)
    }

    override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {
        val playerTextView = p0?.findViewById<TextView>(R.id.textViewPlayer)
        val scoreTextView = p0?.findViewById<TextView>(R.id.textViewScore)
        val timeTextView = p0?.findViewById<TextView>(R.id.textViewTime)
        val difficultyTextView = p0?.findViewById<TextView>(R.id.textViewDifficulty)

        val name = cursor.getString(cursor.getColumnIndexOrThrow(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME))
        val score = cursor.getString(cursor.getColumnIndexOrThrow(HighscoreContract.HighscoresEntry.COLUMN_SCORE))
        val time = cursor.getString(cursor.getColumnIndexOrThrow(HighscoreContract.HighscoresEntry.COLUMN_TIME))
        val difficulty = cursor.getString(cursor.getColumnIndexOrThrow(HighscoreContract.HighscoresEntry.COLUMN_DIFFICULTY))

        playerTextView?.text = name
        scoreTextView?.text = score
        timeTextView?.text = time
        difficultyTextView?.text = difficulty
    }

}