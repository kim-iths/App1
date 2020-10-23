package com.example.app1

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.core.content.ContextCompat
import com.example.app1.data.HighscoreContract
import com.example.app1.data.HighscoreCursorAdapter
import com.example.app1.fragments.*

class MainActivity : AppCompatActivity() {

//    val playerList = mutableListOf<Player>()
    lateinit var currentPlayer: Player
    lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.window.statusBarColor = ContextCompat.getColor(this, R.color.colorScheme2Dark)
        shared = getSharedPreferences("firstLaunch", MODE_PRIVATE)
        if(shared.getBoolean("firstLaunch", true)) firstLaunch() else Log.e("Main", "inte first???")
//        val testPlayer = Player("Guest")
//        playerList.add(testPlayer)
        currentPlayer = Player("Guest")

        supportActionBar?.hide()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, FragmentMainMenu(), "fragment")
        transaction.commit()

//        addMockHighscore()
//        addMockPlayer()
    }

    private fun addMockPlayer() {
        val values = ContentValues()
        values.put(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME, "sten")

        val uri: Uri? = contentResolver.insert(HighscoreContract.HighscoresEntry.CONTENT_URI_PLAYERS, values)
    }

    fun addMockHighscore(){
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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, FragmentTutorial(),"fragment")
        transaction.commit()
    }

    fun settings(){

    }

    fun firstLaunch(){
        val values = ContentValues()
        values.put(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME, "Guest")

        val uri: Uri? = contentResolver.insert(HighscoreContract.HighscoresEntry.CONTENT_URI_PLAYERS, values)

        Log.e("Main", "first launch!!")

        val editor = shared.edit()
        editor.putBoolean("firstLaunch", false)
        editor.apply()
    }
}

//TODO("Add sounds")
//TODO("Add animations/graphics")
//TODO("In harder difficulties, make the correct button change place after a certain amount of time")

// Done
//TODO("Create a database for highscores")

// Done
//TODO("Create a database for players")

// Done
//TODO("Add vibrations")

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
