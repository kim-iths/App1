package com.example.app1.fragments

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.app1.ButtonAdapter
import com.example.app1.ColorButton
import com.example.app1.GameActivity
import com.example.app1.R
import kotlin.random.Random

class FragmentGame : Fragment(){

    lateinit var grid: GridView
    lateinit var adapter: ButtonAdapter
    lateinit var scoreTextView: TextView
    lateinit var startTextView: TextView
    lateinit var timerTextView: Chronometer

    //difficulty specific
    var roundsUntilTileIncrease = 0
    var roundsPerTileIncrease  = 0
    var timeLimitMilliseconds = 0
    var amountLevels = 0


    var amountToAdd = 3
    var random = 0
    var level = 0
    var colorDark = 0
    var colorMedium = 0
    var colorLight = 0
    var timerActive = false

    lateinit var buttonList: MutableList<ColorButton>

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        val context = (activity as GameActivity)

        grid = view.findViewById(R.id.grid)
        val difficultyTextView = view.findViewById<TextView>(R.id.textViewDifficulty)
        timerTextView = view.findViewById(R.id.textViewTimer)
        scoreTextView = view.findViewById(R.id.textViewScore)
        startTextView = view.findViewById(R.id.textViewStart)

        buttonList = mutableListOf()

        adapter = ButtonAdapter(activity, buttonList)
        grid.adapter = adapter

        val difficulty = context.currentDifficulty
        when(difficulty){
            "easy" -> {
                roundsPerTileIncrease = 2
                timeLimitMilliseconds = 30000
            }
            "medium" -> {
                roundsPerTileIncrease = 1
                timeLimitMilliseconds = 20000
            }
            "hard" -> {
                roundsPerTileIncrease = 1
                timeLimitMilliseconds = 20000
            }
            else -> return null
        }

        difficultyTextView.text = difficulty


        timerTextView.isCountDown = true
        timerTextView.setOnChronometerTickListener{
            if(SystemClock.elapsedRealtime() - it.base >= 1){
                timerTextView.stop()
                context.gameOver()
            }
        }

        // Checks whether or not the correct square was pressed
        grid.setOnItemClickListener { adapterView, view, i, l ->

            when {
                level == 0 -> {
                    startTextView.visibility = TextView.GONE
//                    scoreTextView.text = level.toString()

                    timerTextView.base = SystemClock.elapsedRealtime() + timeLimitMilliseconds
                    timerTextView.start()
                    timerActive = true

                    addTiles()
                    ++level
                }
                i == random -> { // Correct square
                    scoreTextView.text = level.toString()

                    if(roundsUntilTileIncrease == 0) addTiles() else --roundsUntilTileIncrease
                    nextRound(level)
                    ++level
                }
                else -> { // Wrong square
                    context.level = level
                    context.time = (timeLimitMilliseconds + ((SystemClock.elapsedRealtime() - timerTextView.base).toDouble()))/1000
                    context.timeLimitSeconds = timeLimitMilliseconds/1000

                    context.gameOver()
                }
            }
        }
        reset()

        return view
    }

    fun nextRound(count: Int) {
        buttonList[random].color = colorMedium

        random = Random.nextInt(0, grid.numColumns * grid.numColumns)
        buttonList[random].color = colorDark
        adapter.notifyDataSetChanged()
    }

    fun reset(){
        when(Random.nextInt(0,5)){
            0 -> {
                colorDark = R.color.colorScheme1Dark
                colorMedium = R.color.colorScheme1Medium
                colorLight = R.color.colorScheme1Light
            }
            1 -> {
                colorDark = R.color.colorScheme2Dark
                colorMedium = R.color.colorScheme2Medium
                colorLight = R.color.colorScheme2Light
            }
            2 -> {
                colorDark = R.color.colorScheme3Dark
                colorMedium = R.color.colorScheme3Medium
                colorLight = R.color.colorScheme3Light
            }
            3 -> {
                colorDark = R.color.colorScheme4Dark
                colorMedium = R.color.colorScheme4Medium
                colorLight = R.color.colorScheme4Light
            }
            4 -> {
                colorDark = R.color.colorPrimaryDark
                colorMedium = R.color.colorPrimary
                colorLight = R.color.colorPrimary
            }
        }

        //if(timerActive){
            timerActive = false
            timerTextView.base = SystemClock.elapsedRealtime() + timeLimitMilliseconds
            timerTextView.stop()
        //}

        startTextView.visibility = TextView.VISIBLE
        grid.numColumns = 1
        level = 0
        amountToAdd = 3
        random = 0
        scoreTextView.text = "0"

        for(i in 0 until buttonList.size)buttonList.removeLast()
        buttonList.add(ColorButton(colorDark))
        adapter.notifyDataSetChanged()
    }

    fun addTiles(){
        ++grid.numColumns
        for (i in 1..amountToAdd) buttonList.add(ColorButton(colorMedium))
        adapter.notifyDataSetChanged()
        amountToAdd += 2
        roundsUntilTileIncrease = roundsPerTileIncrease
    }
}