package com.example.rickandmortyeksamen2024.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CreateCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val image: String
)


// NYYYY