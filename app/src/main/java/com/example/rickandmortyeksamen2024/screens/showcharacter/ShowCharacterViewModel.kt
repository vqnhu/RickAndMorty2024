package com.example.rickandmortyeksamen2024.screens.showcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyeksamen2024.data.data_classes.Character
import com.example.rickandmortyeksamen2024.data.services.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShowCharacterViewModel : ViewModel() {

    private val _showCharacters = MutableStateFlow<List<Character>>(emptyList())
    val showCharacters = _showCharacters.asStateFlow()

    init{
        viewModelScope.launch {
            getAllCharacters()
        }
    }

      private fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _showCharacters.value = RickAndMortyRepository.getAllCharacters()
            } catch (e: Exception) {
                _showCharacters.value = emptyList()
            }
        }
    }
}
