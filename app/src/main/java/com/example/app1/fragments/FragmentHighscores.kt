package com.example.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.app1.MainActivity
import com.example.app1.R

class FragmentHighscores : Fragment() {

    lateinit var context: MainActivity

    lateinit var buttonShowEasy: Button
    lateinit var buttonShowMedium: Button
    lateinit var buttonShowHard: Button

    var currentDifficulty = "easy"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_highscores, container, false)

        context = (activity as MainActivity)

        val backButton = view.findViewById<TextView>(R.id.buttonBack)

        buttonShowEasy = view.findViewById(R.id.buttonShowScoresEasy)
        buttonShowMedium = view.findViewById(R.id.buttonShowScoresMedium)
        buttonShowHard = view.findViewById(R.id.buttonShowScoresHard)

        val listHighscores = view.findViewById<ListView>(R.id.listViewHighscores)
        listHighscores.emptyView = view.findViewById(R.id.emptyView)

        val outside0 = view.findViewById<LinearLayout>(R.id.outsideView0)
        val outside1 = view.findViewById<LinearLayout>(R.id.outsideView1)

        buttonShowEasy.setOnClickListener { showScores("easy") }
        buttonShowMedium.setOnClickListener { showScores("medium") }
        buttonShowHard.setOnClickListener { showScores("hard") }



        backButton.setOnClickListener { returnToMainMenu() }
        outside0.setOnClickListener { returnToMainMenu() }
        outside1.setOnClickListener { returnToMainMenu() }

        showScores("easy")

        return view
    }

    fun returnToMainMenu(){
        context.returnToMainMenu()
    }

    fun showScores(difficulty: String){
        buttonShowEasy.isEnabled = true
        buttonShowMedium.isEnabled = true
        buttonShowHard.isEnabled = true

        buttonShowEasy.background = null
        buttonShowMedium.background = null
        buttonShowHard.background = null

        when(difficulty){
            "easy" -> {
                currentDifficulty = "easy"

                buttonShowEasy.background = ResourcesCompat.getDrawable(resources, R.drawable.button_background_highscores, null)
                buttonShowEasy.background.setTint(ResourcesCompat.getColor(resources,R.color.colorScheme3Light, null))
//                buttonShowEasy.backgroundTintList = ResourcesCompat.getColorStateList(resources, R.color.colorScheme3Light, null)
                buttonShowEasy.isEnabled = false
            }
            "medium" -> {
                currentDifficulty = "medium"

                buttonShowMedium.background = ResourcesCompat.getDrawable(resources, R.drawable.button_background_highscores, null)
                buttonShowMedium.background.setTint(ResourcesCompat.getColor(resources,R.color.colorScheme4Light, null))
                buttonShowMedium.isEnabled = false
            }
            "hard" -> {
                currentDifficulty = "hard"

                buttonShowHard.background = ResourcesCompat.getDrawable(resources, R.drawable.button_background_highscores, null)
                buttonShowHard.background.setTint(ResourcesCompat.getColor(resources,R.color.colorScheme1Light, null))
                buttonShowHard.isEnabled = false
            }
        }
    }
}