package com.example.app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.system.exitProcess

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main_menu)

        supportActionBar?.hide()

        val currentPlayerButton = findViewById<RelativeLayout>(R.id.buttonCurrentPlayer)
        val currentPlayerTextView = findViewById<TextView>(R.id.textViewCurrentPlayer)
        val playButton = findViewById<RelativeLayout>(R.id.buttonPlay)
        val highscoresButton = findViewById<RelativeLayout>(R.id.buttonHighscores)
        val tutorialButton = findViewById<RelativeLayout>(R.id.buttonTutorial)
        val exitButton = findViewById<RelativeLayout>(R.id.buttonExit)

        val testPlayer = Player("kimpi")

        currentPlayerTextView.text = resources.getText(R.string.current_player).toString() + " " + testPlayer.name

        currentPlayerButton.setOnClickListener { }

        playButton.setOnClickListener {
            val i = Intent(this, GameActivity::class.java)
            i.putExtra("currentPlayer", testPlayer)
            startActivity(i)
        }

        highscoresButton.setOnClickListener { }

        tutorialButton.setOnClickListener { }

        exitButton.setOnClickListener { exitProcess(0) }
    }
}