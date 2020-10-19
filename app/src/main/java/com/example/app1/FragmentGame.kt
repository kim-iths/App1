package com.example.app1

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlin.random.Random

class FragmentGame : Fragment(){

    lateinit var grid: GridView
    lateinit var adapter: ButtonAdapter
    lateinit var scoreTextView: TextView
    lateinit var startTextView: TextView
    lateinit var timerTextView: Chronometer

    var amountToAdd = 3
    var random = 0
    var round = 1
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

        //val resetButton = view.findViewById<Button>(R.id.buttonReset)
        grid = view.findViewById(R.id.grid)
        scoreTextView = view.findViewById(R.id.textViewScore)
        timerTextView = view.findViewById(R.id.textViewTimer)
        startTextView = view.findViewById(R.id.textViewStart)

        buttonList = mutableListOf()

        adapter = ButtonAdapter(activity, buttonList)
        grid.adapter = adapter

        reset()

        //resetButton.setOnClickListener { reset() }

        // Checks whether or not the correct square was pressed
        grid.setOnItemClickListener { adapterView, view, i, l ->
            if(startTextView.visibility == TextView.VISIBLE) startTextView.visibility = TextView.GONE
            if(round == 1){
                timerTextView.base = SystemClock.elapsedRealtime()
                timerTextView.start()
                timerActive = true
            }


            if(i == random){ // Correct square
                scoreTextView.text = round.toString()
                ++grid.numColumns
                round += 1
                nextRound(round)
            }else{ // Wrong square
                /* Toast.makeText(activity, getString(R.string.toast_game_over) + " Your time: "
                        + ((SystemClock.elapsedRealtime() - timerTextView.base).toDouble()/1000), Toast.LENGTH_SHORT).show()
                reset()*/
                (activity as GameActivity).gameOver()
            }
        }

        return view
    }

    fun nextRound(count: Int) {
        buttonList[random].color = colorMedium

        for (i in 1..amountToAdd) buttonList.add(ColorButton(colorMedium))

        random = Random.nextInt(0, (count) * (count))
        buttonList[random].color = colorDark
        adapter.notifyDataSetChanged()
        amountToAdd += 2
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
        round = 1
        amountToAdd = 3
        random = 0
        scoreTextView.text = "0"

        for(i in 0 until buttonList.size)buttonList.removeLast()
        buttonList.add(ColorButton(colorDark))
        //scoreTextView.setTextColor(colorDark)
        adapter.notifyDataSetChanged()
    }

}