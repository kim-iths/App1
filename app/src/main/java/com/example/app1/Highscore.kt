package com.example.app1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app1.fragments.FragmentChooseDifficulty

//class Highscore(val player: Player, val score: Int, val time: Double, val difficulty: String)

@Entity
data class Highscore(@PrimaryKey(autoGenerate = true) val id: Int,
                @ColumnInfo(name = "name") var name: String? = null,
                @ColumnInfo(name = "score")var score: Int = 0,
                @ColumnInfo(name = "time")var time: Double = 0.0,
                @ColumnInfo(name = "difficulty")var difficulty: String? = null)