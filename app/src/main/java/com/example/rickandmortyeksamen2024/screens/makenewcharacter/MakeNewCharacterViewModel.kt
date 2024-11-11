package com.example.rickandmortyeksamen2024.screens.makenewcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyeksamen2024.data.CreateCharacter
import com.example.rickandmortyeksamen2024.data.room.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MakeNewCharacterViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<CreateCharacter>>(emptyList())

    val characters = _characters.asStateFlow()

    // fyll på karakterer til databasen
    fun setCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = CharacterRepository.getCharacters()
        }
    }

    // sende de nye lagrede karakterene til databasen
    fun insertCharachter(character: CreateCharacter) {
        viewModelScope.launch(Dispatchers.IO) {
            val newCharacterId = CharacterRepository.insertCharacter(character)
            if (newCharacterId != 1L) {
                val newCharacter = character.copy(id = newCharacterId.toInt())
                _characters.value += newCharacter
            }
        }
    }


}