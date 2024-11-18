package com.example.rickandmortyeksamen2024.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.rickandmortyeksamen2024.data.CreateCharacter
import java.sql.SQLException

object CharacterRepository {

    private lateinit var _rickAndMortyDatabase: RickAndMortyDatabase
    private val _rickAndMortyDao by lazy { _rickAndMortyDatabase.rickAndMortyDao() }

    // definere og opprettelse av databasen
    fun initializeDatabase(context: Context) {
        _rickAndMortyDatabase = Room.databaseBuilder(
            context,
            klass = RickAndMortyDatabase::class.java,
            "Rick and Morty Database"
        ).build()
    }

    // hente alle karakterene via CharacterDao
    suspend fun getCharacters(): List<CreateCharacter> {
        try {
            return _rickAndMortyDao.getCharacters()
        } catch (e: SQLException) {
            Log.d("Database feil", e.toString())
            return emptyList()
        } catch (e: Exception) {
            Log.d("noe har gått feil", e.toString())
            return emptyList()
        }
    }

    // lage ny karakter via CharacterDao
    suspend fun insertCharacter(character: CreateCharacter): Long {
        try {
            return _rickAndMortyDao.insterCharacter(character)
        } catch (e: SQLException) {
            return -1L
        }
    }

    // slette karakter fra databasen
    suspend fun deleteCharacter(character: CreateCharacter): Int {
        try {
            return _rickAndMortyDao.deleteCharacter(character)
        } catch (e: SQLException) {
            Log.e("somthing went wrong", e.toString())
            return 0
        }
    }

    suspend fun deleteAllCharacter(): Int {
        try {
            return _rickAndMortyDao.deleteAllCharacters()
        } catch (e: SQLException) {
            Log.e("somthing went wrong", e.toString())
            return 0
        }
    }
}