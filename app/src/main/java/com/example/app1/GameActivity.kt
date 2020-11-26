package com.example.app1

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.app1.data.HighscoreContract
import com.example.app1.data.HighscoreCursorAdapter
import com.example.app1.fragments.FragmentGame
import com.example.app1.fragments.FragmentGameOver
import com.example.app1.fragments.FragmentWin
import com.example.app1.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class GameActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var db: AppDatabase

    lateinit var currentPlayer: Player
    var level = 0
    var time = 0.0
    var score = 0
    var timeLimitSeconds = 0

    lateinit var vibrator: Vibrator

    lateinit var currentDifficulty: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //Room
        job = Job()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "highscores")
            .fallbackToDestructiveMigration()
            .build()

        supportActionBar?.hide()
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.colorNotificationBar)

        currentPlayer = intent.getSerializableExtra("currentPlayer") as Player
        currentDifficulty = intent.getStringExtra("difficulty").toString()

        vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        startGame()

        val backButton = findViewById<Button>(R.id.buttonBack)
        val restartButton = findViewById<Button>(R.id.buttonRestart)
        val currentPlayerTextView = findViewById<TextView>(R.id.textViewCurrentPlayer)

        backButton.setOnClickListener { finish() }
        restartButton.setOnClickListener { startGame() }
        currentPlayerTextView.text = currentPlayer.name
    }

    fun win() {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.container, FragmentWin(), "fragment")
        transaction.commit()

        score = ((timeLimitSeconds - time) * Math.pow(level.toDouble(), 2.0)).toInt()

        addHighscore(Highscore(0, currentPlayer.name, score, time, currentDifficulty))
    }

    fun gameOver() {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.container, FragmentGameOver(), "fragment")
        transaction.commit()

        score = ((timeLimitSeconds - time) * Math.pow(level.toDouble(), 1.25)).toInt()
        addHighscore(Highscore(0, currentPlayer.name, score, time, currentDifficulty))
    }

    fun startGame() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag("fragment")

        if (fragment != null) {
            transaction.replace(R.id.container, FragmentGame(), "fragment")
        } else {
            transaction.add(R.id.container, FragmentGame(), "fragment")
        }

        transaction.commit()
    }

    fun addHighscore(highscore: Highscore) {
        if (highscore.score == 0) return

        launch(Dispatchers.IO) {
            db.highscoreDao().insert(highscore)
        }
//
//        val values = ContentValues()
//        values.put(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME, highscore.player?.name)
//        values.put(HighscoreContract.HighscoresEntry.COLUMN_SCORE, highscore.score)
//        values.put(HighscoreContract.HighscoresEntry.COLUMN_TIME, highscore.time)
//        values.put(HighscoreContract.HighscoresEntry.COLUMN_DIFFICULTY, highscore.difficulty)
//
//        contentResolver.insert(HighscoreContract.HighscoresEntry.CONTENT_URI, values)
    }

    fun vibrate(amountMs: Long) {
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(amountMs, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            vibrator.vibrate(amountMs)
        }
    }
}