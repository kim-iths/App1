package com.example.app1.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.app1.MainActivity
import com.example.app1.Player
import com.example.app1.PlayerListAdapter
import com.example.app1.R

class FragmentChoosePlayer : Fragment(){

    lateinit var context: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_choose_player, container, false)

        context = (activity as MainActivity)

        val backButton = view.findViewById<TextView>(R.id.buttonBack)
        val addPlayerButton = view.findViewById<ImageView>(R.id.buttonAddPlayer)
        val playerListView = view.findViewById<ListView>(R.id.listPlayers)
        val outside0 = view.findViewById<LinearLayout>(R.id.outsideView0)
        val outside1 = view.findViewById<LinearLayout>(R.id.outsideView1)

        val listAdaper = PlayerListAdapter(context, context.playerList)
        playerListView.adapter = listAdaper



        backButton.setOnClickListener { returnToMainMenu() }
        addPlayerButton.setOnClickListener { addPlayer() }
        playerListView.setOnItemClickListener { adapterView, view, i, l ->

            context.currentPlayer = context.playerList[i]
            returnToMainMenu()
        }

        outside0.setOnClickListener { returnToMainMenu() }
        outside1.setOnClickListener { returnToMainMenu() }

        return view
    }

    fun returnToMainMenu(){
        context.returnToMainMenu()
    }

    fun addPlayer(){
        val dialogBuilder = AlertDialog.Builder(context)

        val editText = EditText(context)

        dialogBuilder.setTitle("Add player").setView(editText)
            .setPositiveButton("Add") { dialogInterface, i ->
                if(editText.text.toString() != ""){
                    context.playerList.add(Player(editText.text.toString()))
                } else Toast.makeText(context, "Enter a username", Toast.LENGTH_SHORT).show()
            }.setNegativeButton("Cancel", null)

        val alert = dialogBuilder.create()
        alert.show()

    }
}