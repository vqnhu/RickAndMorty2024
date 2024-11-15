package com.example.rickandmortyeksamen2024.screens.yourcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyeksamen2024.data.CreateCharacter
import com.example.rickandmortyeksamen2024.data.room.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class YourCharacterViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<CreateCharacter>>(emptyList())

    // val characters: StateFlow<List<CreateCharacter>> get() = _characters
    val characters = _characters.asStateFlow()

    // oppdaterer karakterene fra grensesnitt
    init {
        viewModelScope.launch {
            loadCharacters()
        }
    }

    // henter karakterene fra databasen
    private suspend fun loadCharacters() {
        _characters.value = CharacterRepository.getCharacters()
    }

    fun setCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = CharacterRepository.getCharacters()
        }
    }

    fun deleteCharacter(character: CreateCharacter) {
        viewModelScope.launch(Dispatchers.IO) {

            val numberDeleted = CharacterRepository.deleteCharacter(character) // slette fra databasen
            if (numberDeleted == 1) {
                val currentList = _characters.value // den linjen her sletter fra databasen, men oppdaterer ikke grensensnittet
                val afterDeleteList = currentList.filter { it != character } // sletter en karakter fra grensesnittet
                _characters.value = afterDeleteList
            }
        }
    }

    fun deleteAllCharacter() {
        viewModelScope.launch(Dispatchers.IO) {

            val numberDeleted =
                CharacterRepository.deleteAllCharacter() // slette alle karakterene fra databasen
            if (numberDeleted > 0) {
                _characters.value = emptyList() // fjerner alle karakterene fra grensesnittet
            }
        }
    }


}
