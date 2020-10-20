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

        val scoreTextView = view.findViewById<TextView>(R.id.textViewScore)
        val timeTextView = view.findViewById<TextView>(R.id.textViewTime)

        val context = (activity as GameActivity)
        scoreTextView.text = resources.getText(R.string.current_player).toString() + " " + context.score.toString()
        timeTextView.text = resources.getText(R.string.current_player).toString() + " " + context.time.toString()

        retryButton.setOnClickListener { (activity as GameActivity).startGame() }
        exitButton.setOnClickListener { (activity as GameActivity).finish() }

        return view
    }
}