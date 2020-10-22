package com.example.app1.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.app1.Highscore
import com.example.app1.R

class HighscoresAdapter(context: Context, val highscores: MutableList<Highscore>) : BaseAdapter() {

    val inflater = LayoutInflater.from(context)

    override fun getCount() = highscores.size

    override fun getItem(p0: Int) = highscores[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflater.inflate(R.layout.list_item_highscore, null, false)

        val scoreTextView = view.findViewById<TextView>(R.id.textViewScore)
        scoreTextView.text = highscores[p0].score.toString()

        return view
    }


}
