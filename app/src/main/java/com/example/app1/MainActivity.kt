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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonList = mutableListOf<Button>()

        buttonList.add(Button(this))

        grid = findViewById(R.id.grid)
        adapter = ButtonAdapter(this, buttonList)
        grid.adapter = adapter

        grid.setOnItemClickListener { adapterView, view, i, l ->
            Log.e("!!!", "$i")
            ++grid.numColumns
            for(j in 1..amountToAdd){
                buttonList.add(Button(this))
            }
            amountToAdd += 2
            adapter.notifyDataSetChanged()
        }

//        makeButtons(4)
    }

    fun makeButtons(count: Int){

        val random = Random.nextInt(0,count+1)
        Log.e("!!!", "$random")

        for(i in 0..count-1){
            grid.addView(Button(this))
            //grid.getChildAt(i).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        }


        grid.getChildAt(random).setBackgroundColor(resources.getColor(R.color.colorPrimary))

        grid.getChildAt(random).setOnClickListener {
            makeButtons(count+2)

        }
    }
}