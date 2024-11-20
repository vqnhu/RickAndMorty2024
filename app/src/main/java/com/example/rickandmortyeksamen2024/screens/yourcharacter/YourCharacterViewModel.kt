package com.example.rickandmortyeksamen2024.screens.yourcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyeksamen2024.data.data_classes.CreateCharacter
import com.example.rickandmortyeksamen2024.data.room.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class YourCharacterViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<CreateCharacter>>(emptyList())

    val characters = _characters.asStateFlow()

    // Viser karaktene på skjermen når man navigerer til skjermen
    init {
        viewModelScope.launch {
            loadCharacters()
        }
    }

    // henter karakterene fra databasen
    private suspend fun loadCharacters() {
        _characters.value = CharacterRepository.getCharacters()
    }

    // Slette en karakter
    fun deleteCharacter(character: CreateCharacter) {
        viewModelScope.launch(Dispatchers.IO) {

            val numberDeleted = CharacterRepository.deleteCharacter(character)
            if (numberDeleted == 1) {
                val currentList = _characters.value // sletter fra databasen
                val afterDeleteList =
                    currentList.filter { it != character } // sletter en karakter fra grensesnittet
                _characters.value = afterDeleteList
            }
        }
    }

    // Slette alle karakterene
    fun deleteAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {

            val numberDeleted =
                CharacterRepository.deleteAllCharacter() // slette alle karakterene fra databasen
            if (numberDeleted > 0) {
                _characters.value = emptyList() // fjerner alle karakterene fra grensesnittet
            }
        }
    }
}
