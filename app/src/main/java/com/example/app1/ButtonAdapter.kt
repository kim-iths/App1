package com.example.app1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView

class ButtonAdapter(context: Context, val buttonList: MutableList<Button>): BaseAdapter() {

    val inflater = LayoutInflater.from(context)



    override fun getCount(): Int {
//        TODO("Not yet implemented")
        return buttonList.size
    }

    override fun getItem(p0: Int): Any {
//        TODO("Not yet implemented")
        return buttonList[p0]
    }

    override fun getItemId(p0: Int): Long {
//        TODO("Not yet implemented")
        return p0.toLong()
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.button_fill, p2, false)

        val button = rowView.findViewById<ImageView>(R.id.button)
        //button.setImageResource(R.drawable.yellow)

        return rowView
    }

}