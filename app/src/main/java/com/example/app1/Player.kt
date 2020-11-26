package com.example.app1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//class Player(val name: String) : Serializable

@Entity
data class Player(@PrimaryKey(autoGenerate = true) val id: Int,
                  @ColumnInfo(name = "name") var name: String? = null) : Serializable