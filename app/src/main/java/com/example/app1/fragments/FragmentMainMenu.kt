package com.example.app1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.app1.GameActivity
import com.example.app1.MainActivity
import com.example.app1.Player
import com.example.app1.R
import kotlinx.android.synthetic.main.fragment_choose_player.*

class FragmentMainMenu : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_menu, container, false)

        val context = (activity as MainActivity)

        val currentPlayerButton = view.findViewById<RelativeLayout>(R.id.buttonCurrentPlayer)
        val currentPlayerTextView = view.findViewById<TextView>(R.id.textViewCurrentPlayer)
        val playButton = view.findViewById<RelativeLayout>(R.id.buttonPlay)
        val highscoresButton = view.findViewById<RelativeLayout>(R.id.buttonHighscores)
        val tutorialButton = view.findViewById<RelativeLayout>(R.id.buttonTutorial)
        val settingsButton = view.findViewById<RelativeLayout>(R.id.buttonSettings)

        currentPlayerTextView.text = resources.getText(R.string.current_player).toString() + " " + context.currentPlayer.name

        currentPlayerButton.setOnClickListener { context.choosePlayer() }

        playButton.setOnClickListener {
            val i = Intent(activity, GameActivity::class.java)
            i.putExtra("currentPlayer", context.currentPlayer)
            startActivity(i)
        }

        highscoresButton.setOnClickListener { }

        tutorialButton.setOnClickListener { }

        settingsButton.setOnClickListener {  }

        return view
    }
}