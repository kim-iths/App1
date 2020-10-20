package com.example.app1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMainMenu : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main_menu, container, false)

        val currentPlayerButton = view.findViewById<RelativeLayout>(R.id.buttonCurrentPlayer)
        val currentPlayerTextView = view.findViewById<TextView>(R.id.textViewCurrentPlayer)
        val playButton = view.findViewById<RelativeLayout>(R.id.buttonPlay)
        val highscoresButton = view.findViewById<RelativeLayout>(R.id.buttonHighscores)
        val tutorialButton = view.findViewById<RelativeLayout>(R.id.buttonTutorial)
        val settingsButton = view.findViewById<RelativeLayout>(R.id.buttonSettings)

        val testPlayer = Player("kimpi")

        currentPlayerTextView.text = resources.getText(R.string.current_player).toString() + " " + testPlayer.name

        currentPlayerButton.setOnClickListener { }

        playButton.setOnClickListener {
            val i = Intent(activity, GameActivity::class.java)
            i.putExtra("currentPlayer", testPlayer)
            startActivity(i)
        }

        highscoresButton.setOnClickListener { }

        tutorialButton.setOnClickListener { }

        settingsButton.setOnClickListener {  }

        return view
    }
}