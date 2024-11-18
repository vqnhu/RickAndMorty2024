package com.example.rickandmortyeksamen2024.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortyeksamen2024.data.CreateCharacter

@Database(
    entities = [CreateCharacter::class],
    version = 6,
    exportSchema = true
)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun rickAndMortyDao(): RickAndMortyDao
}