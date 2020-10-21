package com.example.app1.fragments

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

    var roundsUntilTileIncrease = 0
    var roundsPerTileIncrease  = 0

    var amountToAdd = 3
    var random = 0
    var level = 0
    var colorDark = 0
    var colorMedium = 0
    var colorLight = 0
    var timerActive = false

    lateinit var buttonList: MutableList<ColorButton>

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
        roundsPerTileIncrease = when(difficulty){
            "easy" -> 2
            "medium" -> 1
            "hard" -> 1
            else -> 2
        }

        difficultyTextView.text = difficulty

        reset()

        // Checks whether or not the correct square was pressed
        grid.setOnItemClickListener { adapterView, view, i, l ->
            if(startTextView.visibility == TextView.VISIBLE) startTextView.visibility = TextView.GONE
            if(level == 0){
                timerTextView.base = SystemClock.elapsedRealtime()
                timerTextView.start()
                timerActive = true

                ++level
                addTiles()
            }else if(i == random){ // Correct square
                Log.e("FragmentGame", "rounds per increase: " + roundsPerTileIncrease + ", left: " + roundsUntilTileIncrease)
                scoreTextView.text = level.toString()
                ++level

                if(roundsUntilTileIncrease == 0) addTiles() else --roundsUntilTileIncrease
                nextRound(level)
            }else{ // Wrong square
                context.score = level
                context.time = ((SystemClock.elapsedRealtime() - timerTextView.base).toDouble()/1000)

                context.gameOver()
            }
        }
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

        if(timerActive){
            timerActive = false
            timerTextView.base = SystemClock.elapsedRealtime()
            timerTextView.stop()
        }

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