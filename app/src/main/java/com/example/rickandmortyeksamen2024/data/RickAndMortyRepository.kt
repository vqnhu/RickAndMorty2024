package com.example.rickandmortyeksamen2024.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyRepository {
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    private val _rickAndMortyService = _retrofit.create(RickAndMortyService::class.java)


    suspend fun getAllCharacters(): List<Character> {
        try {
            val response =
                _rickAndMortyService.getAllCharacters() // Ensure this endpoint exists in your service

            if (response.isSuccessful) {
                return response.body()?.results
                    ?: emptyList() // Access the results from the CharacterList
            } else {
                return emptyList()
            }
        } catch (e: Exception) {
            return emptyList()
        }
    }
}