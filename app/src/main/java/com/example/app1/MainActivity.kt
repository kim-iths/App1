package com.example.app1

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var grid: GridView
    lateinit var adapter: ButtonAdapter
    var amountToAdd = 3
    var random = 0
    var round = 1;

    lateinit var buttonList: MutableList<ColorButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonList = mutableListOf()
        buttonList.add(ColorButton(R.color.colorPrimaryDark))

        grid = findViewById(R.id.grid)
        val resetButton = findViewById<Button>(R.id.buttonReset)
        adapter = ButtonAdapter(this, buttonList)
        grid.adapter = adapter

        resetButton.setOnClickListener {
            reset()
        }

        grid.setOnItemClickListener { adapterView, view, i, l ->

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
        buttonList[random].color = R.color.colorPrimary

        for (i in 1..amountToAdd) {
            buttonList.add(ColorButton(R.color.colorPrimary))
        }

        random = Random.nextInt(0, (count) * (count))
        Log.e("!!!", "count: $count, random: $random")
        buttonList[random].color = R.color.colorPrimaryDark
        adapter.notifyDataSetChanged()
        amountToAdd += 2
    }

    fun reset(){
        grid.numColumns = 1
        round = 1
        amountToAdd = 3
        random = 0
        for(i in 0 until buttonList.size-1)buttonList.removeLast()
        buttonList[0].color = R.color.colorPrimaryDark
    }
}


//TODO("In harder difficulties, make the correct button change place after a certain amount of time")