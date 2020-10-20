package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, FragmentMainMenu(), "fragment")
        transaction.commit()
    }

    fun choosePlayer(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FragmentChoosePlayer(),"fragment")
        transaction.commit()

    }

    fun returnToMainMenu(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FragmentMainMenu(),"fragment")
        transaction.commit()
    }

    fun play(){

    }

    fun highscores(){

    }

    fun tutorial(){

    }

    fun settings(){

    }

}


//TODO("Add sounds")
//TODO("Add animations/graphics")
//TODO("User selection? Guest player?")

// Done
//TODO("Game over screen")

// In progress
//TODO("Difficulty selection")
//TODO("In harder difficulties, make the correct button change place after a certain amount of time")

// Done
//TODO("Make use of fragments for main menu and in game")

// In progress
//TODO("Main menu")

// Done
//TODO("Timer at the top of the screen that starts after pressing the first button")

// Done
//TODO("Score counter either underneath the grid or next to the timer")
