package com.example.app1

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.zip.Inflater

class PlayerListAdapter(val context: Context, val listPlayers: MutableList<Player>) : BaseAdapter() {

    val inflater = LayoutInflater.from(context)

    override fun getCount() = listPlayers.size

    override fun getItem(p0: Int) = listPlayers[p0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflater.inflate(R.layout.list_item_player, null, false)

        val playerTextView = view.findViewById<TextView>(R.id.textViewPlayer)
        playerTextView.text = listPlayers[p0].name

        return view
    }
}