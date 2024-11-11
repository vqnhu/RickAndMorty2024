package com.example.rickandmortyeksamen2024.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyService {
    @GET("character/?format=json")
    suspend fun getAllCharacters(): Response<CharacterList> // This fetches all characters
}