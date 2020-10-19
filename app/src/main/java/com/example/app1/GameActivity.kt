package com.example.app1

import android.graphics.drawable.DrawableContainer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar?.hide()

        startGame()

        val backButton = findViewById<Button>(R.id.buttonBack)
        val restartButton = findViewById<Button>(R.id.buttonRestart)

        backButton.setOnClickListener { finish() }
        restartButton.setOnClickListener { startGame() }

    }

    fun gameOver(){
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.container,FragmentGameOver(), "gameOverFragment")
        transaction.commit()
    }

    fun startGame(){
        val transaction = supportFragmentManager.beginTransaction()

        val fragment = supportFragmentManager.findFragmentByTag("gameOverFragment")

        if(fragment != null){
            Log.e("GameActivity", "fragment exists")
            transaction.replace(R.id.container, FragmentGame(), "gameFragment")
        }else{
            transaction.add(R.id.container, FragmentGame(), "gameFragment")
        }

        transaction.commit()
    }


}