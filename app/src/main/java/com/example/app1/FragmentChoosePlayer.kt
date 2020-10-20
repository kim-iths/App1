package com.example.app1

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

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
        val outside0 = view.findViewById<LinearLayout>(R.id.outsideView0)
        val outside1 = view.findViewById<LinearLayout>(R.id.outsideView1)


        backButton.setOnClickListener { returnToMainMenu() }
        outside0.setOnClickListener { returnToMainMenu() }
        outside1.setOnClickListener { returnToMainMenu() }

        return view
    }

    fun returnToMainMenu(){
        context.returnToMainMenu()
    }

}