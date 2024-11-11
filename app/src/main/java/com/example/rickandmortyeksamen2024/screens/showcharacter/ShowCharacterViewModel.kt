package com.example.rickandmortyeksamen2024.screens.showcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyeksamen2024.data.Character
import com.example.rickandmortyeksamen2024.data.RickAndMortyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShowCharacterViewModel : ViewModel() {
    // En ViewModel har 1 eller flere states; state for produkt
    private val _showCharacters = MutableStateFlow<List<Character>>(emptyList())
    val showCharacters = _showCharacters.asStateFlow()


    fun showCharacter() {
        viewModelScope.launch {
            _showCharacters.value = RickAndMortyRepository.getAllCharacters()
        }
    }
}
