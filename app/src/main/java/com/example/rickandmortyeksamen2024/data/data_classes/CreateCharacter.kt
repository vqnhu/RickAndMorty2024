package com.example.rickandmortyeksamen2024.data.data_classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CreateCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val species: String,
    val status: String,
    val image: Int
)
