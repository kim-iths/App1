package com.example.app1

import android.app.ActionBar
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var grid: GridView
    lateinit var adapter: ButtonAdapter
    var amountToAdd = 3
    var random = 0
    var round = 1
    var colorDark = 0
    var colorMedium = 0
    var colorLight = 0

    lateinit var buttonList: MutableList<ColorButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resetButton = findViewById<Button>(R.id.buttonReset)
        val startText = findViewById<TextView>(R.id.textViewStart)
        grid = findViewById(R.id.grid)

        buttonList = mutableListOf()

        adapter = ButtonAdapter(this, buttonList)
        grid.adapter = adapter

        reset()

        resetButton.setOnClickListener { reset() }

        grid.setOnItemClickListener { adapterView, view, i, l ->
            if(startText.visibility == TextView.VISIBLE) startText.visibility = TextView.GONE

            if(i == random){
                Log.e("!!!", "$i")
                ++grid.numColumns
                round += 1
                nextRound(round)
            }else{
                Toast.makeText(this, getString(R.string.toast_game_over), Toast.LENGTH_SHORT).show()
                reset()
            }
        }
    }

    fun nextRound(count: Int) {
        buttonList[random].color = colorMedium

        for (i in 1..amountToAdd) {
            buttonList.add(ColorButton(colorMedium))
        }

        random = Random.nextInt(0, (count) * (count))
        Log.e("!!!", "count: $count, random: $random")
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

        textViewStart.visibility = TextView.VISIBLE
        grid.numColumns = 1
        round = 1
        amountToAdd = 3
        random = 0
        for(i in 0 until buttonList.size)buttonList.removeLast()
        buttonList.add(ColorButton(colorDark))
        adapter.notifyDataSetChanged()
    }
}
//TODO("Difficulty selection")
//TODO("In harder difficulties, make the correct button change place after a certain amount of time")
//TODO("Main menu?")
//TODO("Timer at the top of the screen that starts after pressing the first button")
//TODO("Score counter either underneath the grid or next to the timer")
