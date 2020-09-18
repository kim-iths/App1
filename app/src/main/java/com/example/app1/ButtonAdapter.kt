package com.example.app1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button

class ButtonAdapter(val context: Context, var listButtons: MutableList<ColorButton>): BaseAdapter() {

    val inflater = LayoutInflater.from(context)

    override fun getCount() = listButtons.size

    override fun getItem(p0: Int) = listButtons[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.image_square, p2, false)
        rowView.setBackgroundColor(context.resources.getColor(listButtons[p0].color))
        return rowView
    }
}

class ColorButton(var color: Int){

}