package com.example.rickandmortyeksamen2024.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyeksamen2024.data.CreateCharacter

@Dao
interface RickAndMortyDao {

    @Query("SELECT * FROM CreateCharacter")
    suspend fun getCharacters(): List<CreateCharacter>

    @Query("DELETE FROM CreateCharacter")
    suspend fun deleteAllCharacters(): Int

    // lage karakter
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insterCharacter(character: CreateCharacter): Long

    // spørringsmetode for å slette karakter
    @Delete
    suspend fun deleteCharacter(character: CreateCharacter): Int



}