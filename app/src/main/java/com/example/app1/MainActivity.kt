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
import androidx.room.Room
import com.example.app1.data.HighscoreContract
import com.example.app1.data.HighscoreCursorAdapter
import com.example.app1.fragments.*
import com.example.app1.room.AppDatabase
import com.example.app1.room.AppDatabasePlayers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var db: AppDatabasePlayers

    lateinit var currentPlayer: Player
    lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Room
        job = Job()
        db = Room.databaseBuilder(applicationContext, AppDatabasePlayers::class.java, "players")
            .fallbackToDestructiveMigration()
            .build()

        this.window.statusBarColor = ContextCompat.getColor(this, R.color.colorScheme2Dark)
        shared = getSharedPreferences("firstLaunch", MODE_PRIVATE)
        if(shared.getBoolean("firstLaunch", true)) firstLaunch()
        currentPlayer = Player(0, "Guest")

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

    fun settings(){}

    fun firstLaunch(){
        launch(Dispatchers.IO) {
            db.playerDao().insert(Player(0, "Guest"))
        }

//        val values = ContentValues()
//        values.put(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME, "Guest")
//        contentResolver.insert(HighscoreContract.HighscoresEntry.CONTENT_URI_PLAYERS, values)

        val editor = shared.edit()
        editor.putBoolean("firstLaunch", false)
        editor.apply()
    }
}

//TODO("Add sounds")
//TODO("Add animations/graphics")

// Tried
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
