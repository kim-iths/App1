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
    var round = 1
    lateinit var buttonList: MutableList<ColorButton>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonList = mutableListOf()
        buttonList.add(ColorButton(R.color.colorPrimaryDark))

        grid = findViewById(R.id.grid)
        adapter = ButtonAdapter(this, buttonList)
        grid.adapter = adapter

        //Log.e("!!!", "${buttonList.size}")

        grid.setOnItemClickListener { adapterView, view, i, l ->

            Log.e("!!!", "$i")
            ++grid.numColumns
            for(j in 1..amountToAdd){
                buttonList.add(ColorButton(R.color.colorPrimary))
            }
            //nextRound((amountToAdd-1)*(amountToAdd-1))
            //buttonList[random].color = R.color.colorPrimaryDark

            round += 1
            buttonList[random].color = R.color.colorPrimary
            nextRound(round)
            adapter.notifyDataSetChanged()
            amountToAdd += 2
        }

    }

    fun nextRound(count: Int){
        random = Random.nextInt(0,(count) * (count))
        Log.e("!!!", "count: $count, random: $random")
        buttonList[random].color = R.color.colorPrimaryDark

        //for(i in 0..count-1){


            //buttonList.add(ColorButton(R.color.colorPrimary))
            //grid.getChildAt(i).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        //}

        //grid.getChildAt(random).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))

        //grid.getChildAt(random).setOnClickListener {
            //makeButtons(count+2)
        //}
    }
}