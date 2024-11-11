package com.example.rickandmortyeksamen2024.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortyeksamen2024.data.CreateCharacter

@Database(
    entities = [CreateCharacter::class],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun rickAndMortyDao(): RickAndMortyDao

    private val characters = mutableListOf<CreateCharacter>()

    // henter karaterene
    fun getCharacters(): List<CreateCharacter> {
        return characters
    }

    // lage karakter
    fun addCharacter(character: CreateCharacter) {
        characters.add(character)
    }

}