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

class PlayerCursorAdapter(val context: Context, cursor: Cursor?) : CursorAdapter(context, cursor, 0) {

    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.list_item_player, p2, false)
    }

    override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {
        val nameTextView = p0?.findViewById<TextView>(R.id.textViewPlayer)

        val name = cursor.getString(cursor.getColumnIndexOrThrow(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME))

        nameTextView?.text = name
    }

}