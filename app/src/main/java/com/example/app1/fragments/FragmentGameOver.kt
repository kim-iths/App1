package com.example.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.app1.GameActivity
import com.example.app1.R

class FragmentGameOver : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_over, container, false)

        val retryButton = view.findViewById<Button>(R.id.buttonRetry)
        val exitButton = view.findViewById<Button>(R.id.buttonExit)

        val levelTextView = view.findViewById<TextView>(R.id.textViewLevel)
        val timeTextView = view.findViewById<TextView>(R.id.textViewTime)
        val scoreTextView = view.findViewById<TextView>(R.id.textViewScore)

        val context = (activity as GameActivity)
        levelTextView.text = resources.getText(R.string.level).toString() + " " + context.level.toString()
        timeTextView.text = resources.getText(R.string.time).toString() + " " + context.time.toString()
        scoreTextView.text = resources.getText(R.string.final_score).toString() + " " + context.score.toString()

        retryButton.setOnClickListener { (activity as GameActivity).startGame() }
        exitButton.setOnClickListener { (activity as GameActivity).finish() }

        context.vibrate(400)

        return view
    }
}