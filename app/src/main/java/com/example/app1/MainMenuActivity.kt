package com.example.app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.system.exitProcess

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        supportActionBar?.hide()

        val startButton = findViewById<Button>(R.id.buttonStart)
        val highscoresButton = findViewById<Button>(R.id.buttonHighscores)
        val tutorialButton = findViewById<Button>(R.id.buttonTutorial)
        val exitButton = findViewById<Button>(R.id.buttonExit)


        startButton.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        exitButton.setOnClickListener { exitProcess(0) }
    }
}