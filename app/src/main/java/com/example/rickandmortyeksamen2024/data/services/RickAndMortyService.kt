package com.example.rickandmortyeksamen2024.data.services

import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("character/?format=json")
    suspend fun getAllCharacters(): Response<CharacterList> // Dette henter alle karakterer
}