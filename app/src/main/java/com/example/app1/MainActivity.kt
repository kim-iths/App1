package com.example.app1

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.view.get
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var grid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grid = findViewById(R.id.gridLayout)

        grid.alignmentMode = GridLayout.ALIGN_BOUNDS
        makeButtons(4)
    }

    fun makeButtons(count: Int){
        grid.removeAllViews()

        grid.columnCount = count/2
        grid.rowCount = count/2

        val random = Random.nextInt(0,count+1)
        Log.e("!!!", "$random")

        for(i in 0..count-1){
            grid.addView(Button(this))
            grid.getChildAt(i).setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        }


        grid.getChildAt(random).setBackgroundColor(resources.getColor(R.color.colorPrimary))
        grid.getChildAt(random).setOnClickListener {
            makeButtons(count+2)

        }
    }
}