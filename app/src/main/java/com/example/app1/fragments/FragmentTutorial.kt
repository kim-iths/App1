package com.example.app1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.app1.GameActivity
import com.example.app1.MainActivity
import com.example.app1.R
import kotlinx.android.synthetic.main.fragment_tutorial.*

class FragmentTutorial : Fragment() {

    var page = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tutorial, container, false)

        val context = (activity as MainActivity)


        val backButton = view.findViewById<TextView>(R.id.buttonBack)

        val tutorialImageView = view.findViewById<ImageView>(R.id.imageViewTutorial)
        val tutorialTextView = view.findViewById<TextView>(R.id.textViewTutorial)
        val nextButton = view.findViewById<Button>(R.id.buttonNext)


        val outside0 = view.findViewById<LinearLayout>(R.id.outsideView0)
        val outside1 = view.findViewById<LinearLayout>(R.id.outsideView1)


        nextButton.setOnClickListener {

            if(page == 1) context.returnToMainMenu()

            tutorialImageView.setImageResource(R.drawable.tutorial2)
            tutorialTextView.text = "Win by pressing all the correct tiles. Press the wrong tile or letting the time run out and it's game over!"
            nextButton.text = "Done"

            ++page
        }

        backButton.setOnClickListener { context.returnToMainMenu() }
        outside0.setOnClickListener { context.returnToMainMenu() }
        outside1.setOnClickListener { context.returnToMainMenu() }

        return view
    }
}