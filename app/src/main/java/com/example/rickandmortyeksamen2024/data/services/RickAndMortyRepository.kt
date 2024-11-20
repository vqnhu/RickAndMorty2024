package com.example.rickandmortyeksamen2024.data.services

import com.example.rickandmortyeksamen2024.data.data_classes.Character
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyRepository {

    // Setter opp en OkHttpClient med en logging for å logge HTTP-responser
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY // Logger hele forespørselene og svarene
            )
        ).build()

    // Setter opp Retrofit for å håndtere en API-anrop
    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://rickandmortyapi.com/api/") // angir URL for APIet
        .addConverterFactory(
            GsonConverterFactory.create() // Bruker Gson for å konvertere JSON-objekter til Kotlin
        ).build()

    // Oppretter en instans av RickAndMortyService for å gjøre API-anrop
    private val _rickAndMortyService = _retrofit.create(RickAndMortyService::class.java)

    // Funksjon for å hente alle karakterer fra APIet
    suspend fun getAllCharacters(): List<Character> {
        try {
            val response =
                _rickAndMortyService.getAllCharacters()

            if (response.isSuccessful) {
                return response.body()?.results
                    ?: emptyList()
            } else {
                return emptyList()
            }
        } catch (e: Exception) {
            return emptyList()
        }
    }
}