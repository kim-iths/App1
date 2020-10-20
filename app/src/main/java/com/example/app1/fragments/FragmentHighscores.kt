package com.example.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.app1.MainActivity
import com.example.app1.R

class FragmentHighscores : Fragment() {

    lateinit var context: MainActivity

    lateinit var buttonShowEasy: Button
    lateinit var buttonShowMedium: Button
    lateinit var buttonShowHard: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_highscores, container, false)

        context = (activity as MainActivity)

        val backButton = view.findViewById<TextView>(R.id.buttonBack)

        buttonShowEasy = view.findViewById<Button>(R.id.buttonShowScoresEasy)
        buttonShowMedium = view.findViewById<Button>(R.id.buttonShowScoresMedium)
        buttonShowHard = view.findViewById<Button>(R.id.buttonShowScoresHard)

        val outside0 = view.findViewById<LinearLayout>(R.id.outsideView0)
        val outside1 = view.findViewById<LinearLayout>(R.id.outsideView1)

        buttonShowEasy.setOnClickListener { showScores("easy") }
        buttonShowMedium.setOnClickListener { showScores("medium") }
        buttonShowHard.setOnClickListener { showScores("hard") }


        backButton.setOnClickListener { returnToMainMenu() }
        outside0.setOnClickListener { returnToMainMenu() }
        outside1.setOnClickListener { returnToMainMenu() }

        return view
    }

    fun returnToMainMenu(){
        context.returnToMainMenu()
    }

    fun showScores(difficulty: String){
        when(difficulty){
            "easy" -> {
                buttonShowEasy.isEnabled = false
                buttonShowMedium.isEnabled = true
                buttonShowHard.isEnabled = true
            }
            "medium" -> {
                buttonShowEasy.isEnabled = true
                buttonShowMedium.isEnabled = false
                buttonShowHard.isEnabled = true
            }
            "hard" -> {
                buttonShowEasy.isEnabled = true
                buttonShowMedium.isEnabled = true
                buttonShowHard.isEnabled = false
            }
        }
    }
}