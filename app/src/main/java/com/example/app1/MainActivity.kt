package com.example.app1

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.app1.data.HighscoreContract
import com.example.app1.data.HighscoreCursorAdapter
import com.example.app1.fragments.FragmentChooseDifficulty
import com.example.app1.fragments.FragmentChoosePlayer
import com.example.app1.fragments.FragmentHighscores
import com.example.app1.fragments.FragmentMainMenu

class MainActivity : AppCompatActivity() {

    val playerList = mutableListOf<Player>()
    lateinit var currentPlayer: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shared = getSharedPreferences("app", Context.MODE_PRIVATE)

        val testPlayer = Player("Guest")
        playerList.add(testPlayer)
        currentPlayer = playerList[0]

        supportActionBar?.hide()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, FragmentMainMenu(), "fragment")
        transaction.commit()

//        addMockHighscores()
    }

    fun addMockHighscores(){
        val values = ContentValues()
        values.put(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME, "kimpi")
        values.put(HighscoreContract.HighscoresEntry.COLUMN_SCORE, 928)
        values.put(HighscoreContract.HighscoresEntry.COLUMN_TIME, 12.12)
        values.put(HighscoreContract.HighscoresEntry.COLUMN_DIFFICULTY, "easy")

        val uri: Uri? = contentResolver.insert(HighscoreContract.HighscoresEntry.CONTENT_URI, values)
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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FragmentChooseDifficulty(),"fragment")
        transaction.commit()
    }

    fun highscores(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FragmentHighscores(),"fragment")
        transaction.commit()
    }

    fun tutorial(){

    }

    fun settings(){

    }
}

//TODO("Add sounds")
//TODO("Add animations/graphics")
//TODO("In harder difficulties, make the correct button change place after a certain amount of time")

// In progress
//TODO("Create a database for highscores")
//TODO("Create a database for players")

// Done
//TODO("Create different gameplay for each difficulty")

// Done
//TODO("User selection? Guest player?")

// Done
//TODO("Game over screen")

// Done
//TODO("Difficulty selection")

// Done
//TODO("Make use of fragments for main menu and in game")

// Done
//TODO("Main menu")

// Done
//TODO("Timer at the top of the screen that starts after pressing the first button")

// Done
//TODO("Score counter either underneath the grid or next to the timer")
