package com.example.app1.fragments

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.room.Room
import com.example.app1.Highscore
import com.example.app1.MainActivity
import com.example.app1.R
import com.example.app1.data.HighscoreContract
import com.example.app1.data.HighscoreCursorAdapter
import com.example.app1.room.AppDatabase
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import kotlin.coroutines.CoroutineContext

class FragmentHighscores : Fragment(), LoaderManager.LoaderCallbacks<Cursor>, CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var db: AppDatabase

    val highscoresList = mutableListOf<Highscore>()
    lateinit var highscoresAdapter: HighscoresAdapter

    lateinit var mHighscoreCursorAdapter: HighscoreCursorAdapter

    lateinit var context: MainActivity

    lateinit var buttonShowEasy: Button
    lateinit var buttonShowMedium: Button
    lateinit var buttonShowHard: Button

    var currentDifficulty = "easy"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_highscores, container, false)
        context = (activity as MainActivity)

        //Room
        job = Job()
        db = Room.databaseBuilder(context, AppDatabase::class.java, "highscores")
            .fallbackToDestructiveMigration()
            .build()

        highscoresAdapter = HighscoresAdapter(context, highscoresList)

//        mHighscoreCursorAdapter = HighscoreCursorAdapter(context, null)
//        loaderManager.initLoader(0, null, this)

        val backButton = view.findViewById<TextView>(R.id.buttonBack)
        buttonShowEasy = view.findViewById(R.id.buttonShowScoresEasy)
        buttonShowMedium = view.findViewById(R.id.buttonShowScoresMedium)
        buttonShowHard = view.findViewById(R.id.buttonShowScoresHard)

        val listHighscores = view.findViewById<ListView>(R.id.listViewHighscores)
        listHighscores.emptyView = view.findViewById(R.id.emptyView)
//        listHighscores.adapter = mHighscoreCursorAdapter
        listHighscores.adapter = highscoresAdapter


        val outside0 = view.findViewById<LinearLayout>(R.id.outsideView0)
        val outside1 = view.findViewById<LinearLayout>(R.id.outsideView1)

        buttonShowEasy.setOnClickListener { showScores("easy") }
        buttonShowMedium.setOnClickListener { showScores("medium") }
        buttonShowHard.setOnClickListener { showScores("hard") }

        backButton.setOnClickListener { returnToMainMenu() }
        outside0.setOnClickListener { returnToMainMenu() }
        outside1.setOnClickListener { returnToMainMenu() }

        showScores("easy")

        return view
    }

    fun returnToMainMenu(){
        context.returnToMainMenu()
    }

    fun showScores(difficulty: String){
        buttonShowEasy.isEnabled = true
        buttonShowMedium.isEnabled = true
        buttonShowHard.isEnabled = true

        buttonShowEasy.background = null
        buttonShowMedium.background = null
        buttonShowHard.background = null

        when(difficulty){
            "easy" -> {
                currentDifficulty = "easy"

                buttonShowEasy.background = ResourcesCompat.getDrawable(resources, R.drawable.button_background_highscores, null)
                buttonShowEasy.background.setTint(ResourcesCompat.getColor(resources,R.color.colorScheme3Light, null))
                buttonShowEasy.isEnabled = false

//                loaderManager.restartLoader(0, null, this)
            }
            "medium" -> {
                currentDifficulty = "medium"

                buttonShowMedium.background = ResourcesCompat.getDrawable(resources, R.drawable.button_background_highscores, null)
                buttonShowMedium.background.setTint(ResourcesCompat.getColor(resources,R.color.colorScheme4Light, null))
                buttonShowMedium.isEnabled = false

//                loaderManager.restartLoader(0, null, this)
            }
            "hard" -> {
                currentDifficulty = "hard"

                buttonShowHard.background = ResourcesCompat.getDrawable(resources, R.drawable.button_background_highscores, null)
                buttonShowHard.background.setTint(ResourcesCompat.getColor(resources,R.color.colorScheme1Light, null))
                buttonShowHard.isEnabled = false

//                loaderManager.restartLoader(0, null, this)
            }
        }

        val list = loadByDifficulty(currentDifficulty)

        GlobalScope.launch(Dispatchers.Main) {
            if(highscoresList.size > 0) highscoresList.clear()

            val highscores = list.await()
            for(highscore in highscores) {
                highscoresList.add(highscore)
                Log.e("!!!", "highscore: $highscore")
            }
            highscoresAdapter.notifyDataSetChanged()
        }



    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val selection = "${HighscoreContract.HighscoresEntry.COLUMN_DIFFICULTY} = ?"

        val selectionArgs: Array<String>
        when(currentDifficulty){
            "easy" -> selectionArgs = arrayOf("easy")
            "medium" -> selectionArgs = arrayOf("medium")
            "hard" -> selectionArgs = arrayOf("hard")
            else -> throw IllegalArgumentException("Not a valid difficulty")
        }

        val projection = arrayOf(
            HighscoreContract.HighscoresEntry._ID,
            HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME,
            HighscoreContract.HighscoresEntry.COLUMN_SCORE,
            HighscoreContract.HighscoresEntry.COLUMN_TIME,
            HighscoreContract.HighscoresEntry.COLUMN_DIFFICULTY)

        return CursorLoader(context, HighscoreContract.HighscoresEntry.CONTENT_URI, projection, selection, selectionArgs,
        HighscoreContract.HighscoresEntry.COLUMN_SCORE + " DESC")
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        mHighscoreCursorAdapter.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        mHighscoreCursorAdapter.swapCursor(null)
    }


    fun loadByDifficulty(difficulty: String) : Deferred<List<Highscore>> =
        async(Dispatchers.IO) {
            db.highscoreDao().findByDifficulty(difficulty)
        }


}