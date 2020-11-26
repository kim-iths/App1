package com.example.app1.fragments

import android.app.AlertDialog
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.room.Room
import com.example.app1.MainActivity
import com.example.app1.Player
import com.example.app1.PlayerListAdapter
import com.example.app1.R
import com.example.app1.data.HighscoreContract
import com.example.app1.data.HighscoreCursorAdapter
import com.example.app1.data.PlayerCursorAdapter
import com.example.app1.room.AppDatabase
import com.example.app1.room.AppDatabasePlayers
import kotlinx.android.synthetic.main.fragment_highscores.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FragmentChoosePlayer : Fragment(), LoaderManager.LoaderCallbacks<Cursor>, CoroutineScope{

    lateinit var playerAdaper: PlayerListAdapter
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var db: AppDatabasePlayers

    val playerList = mutableListOf<Player>()
    lateinit var mPlayerCursorAdapter: PlayerCursorAdapter
    lateinit var context: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_choose_player, container, false)

        context = (activity as MainActivity)

        //Room
        job = Job()
        db = Room.databaseBuilder(context, AppDatabasePlayers::class.java, "players")
            .fallbackToDestructiveMigration()
            .build()

//        mPlayerCursorAdapter = PlayerCursorAdapter(context, null)
//        loaderManager.initLoader(0, null, this)

        val backButton = view.findViewById<TextView>(R.id.buttonBack)
        val addPlayerButton = view.findViewById<ImageView>(R.id.buttonAddPlayer)
        val playerListView = view.findViewById<ListView>(R.id.listPlayers)
        val outside0 = view.findViewById<LinearLayout>(R.id.outsideView0)
        val outside1 = view.findViewById<LinearLayout>(R.id.outsideView1)


//        playerListView.adapter = mPlayerCursorAdapter
        playerAdaper = PlayerListAdapter(context, playerList)
        playerListView.adapter = playerAdaper

        backButton.setOnClickListener { returnToMainMenu() }
        addPlayerButton.setOnClickListener { addPlayer() }
        playerListView.setOnItemClickListener { adapterView, view, i, l ->
//            val currentPlayer = mPlayerCursorAdapter.cursor.getString(mPlayerCursorAdapter.cursor.getColumnIndex("name"))

            val currentPlayer = playerList[i].name
            context.currentPlayer = Player(0, currentPlayer)
//            context.currentPlayer = context.playerList[i]
            returnToMainMenu()
        }

        outside0.setOnClickListener { returnToMainMenu() }
        outside1.setOnClickListener { returnToMainMenu() }

        getPlayers()

        return view
    }

    fun returnToMainMenu(){
        context.returnToMainMenu()
    }

    fun addPlayer(){
        val dialogBuilder = AlertDialog.Builder(context)

        val editText = EditText(context)

        dialogBuilder.setTitle("Add player").setView(editText)
            .setPositiveButton("Add") { dialogInterface, i ->
                if(editText.text.toString() != ""){
                    addPlayer(editText.text.toString())

                } else Toast.makeText(context, "Enter a username", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("Cancel", null)

        val alert = dialogBuilder.create()
        alert.show()
    }

    fun addPlayer(name: String){
        launch(Dispatchers.IO) {
            db.playerDao().insert(Player(0, name))
            getPlayers()
        }


//        val values = ContentValues()
//        values.put(HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME, name)

//        val uri: Uri? = context.contentResolver.insert(HighscoreContract.HighscoresEntry.CONTENT_URI_PLAYERS, values)
//        loaderManager.restartLoader(0,null,this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val projection = arrayOf(
            HighscoreContract.HighscoresEntry._ID,
            HighscoreContract.HighscoresEntry.COLUMN_PLAYER_NAME)

        return CursorLoader(context, HighscoreContract.HighscoresEntry.CONTENT_URI_PLAYERS, projection, null, null, null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        mPlayerCursorAdapter.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        mPlayerCursorAdapter.swapCursor(null)
    }

    fun getPlayers(){
        val list = loadAllPlayers()

        GlobalScope.launch(Dispatchers.Main) {
            if(playerList.size > 0) playerList.clear()

            val players = list.await()
            for(player in players) {
                playerList.add(player)
                Log.e("!!!", "highscore: $player")
            }
            playerAdaper.notifyDataSetChanged()
        }
    }

    fun loadAllPlayers() : Deferred<List<Player>> =
        async(Dispatchers.IO) {
            db.playerDao().getAll()
        }
}