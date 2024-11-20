package com.example.rickandmortyeksamen2024.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyeksamen2024.data.data_classes.CreateCharacter

@Dao
interface RickAndMortyDao {

    // Spøørring for å hente alle karakterene
    @Query("SELECT * FROM CreateCharacter")
    suspend fun getCharacters(): List<CreateCharacter>

    // Spørring for å slette av alle karakterene
    @Query("DELETE FROM CreateCharacter")
    suspend fun deleteAllCharacters(): Int

    // Lage karakter
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insterCharacter(character: CreateCharacter): Long

    // Spørringsmetode for å slette karakter
    @Delete
    suspend fun deleteCharacter(character: CreateCharacter): Int
}