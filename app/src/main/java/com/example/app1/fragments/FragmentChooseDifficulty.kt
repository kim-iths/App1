package com.example.app1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.app1.GameActivity
import com.example.app1.MainActivity
import com.example.app1.R

class FragmentChooseDifficulty : Fragment(){

    lateinit var context: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_choose_difficulty, container, false)

        context = (activity as MainActivity)

        val backButton = view.findViewById<TextView>(R.id.buttonBack)

        val buttonEasy = view.findViewById<RelativeLayout>(R.id.buttonEasy)
        val buttonMedium = view.findViewById<RelativeLayout>(R.id.buttonMedium)
        val buttonHard = view.findViewById<RelativeLayout>(R.id.buttonHard)

        val outside0 = view.findViewById<LinearLayout>(R.id.outsideView0)
        val outside1 = view.findViewById<LinearLayout>(R.id.outsideView1)

        backButton.setOnClickListener { context.returnToMainMenu() }

        buttonEasy.setOnClickListener { startGame("easy") }
        buttonMedium.setOnClickListener { startGame("medium") }
        buttonHard.setOnClickListener { startGame("hard") }

        outside0.setOnClickListener { context.returnToMainMenu() }
        outside1.setOnClickListener { context.returnToMainMenu() }

        return view
    }


    fun startGame(difficulty: String){
        val i = Intent(activity, GameActivity::class.java)
        i.putExtra("currentPlayer", context.currentPlayer)
        i.putExtra("difficulty", difficulty)
        startActivity(i)
    }
}