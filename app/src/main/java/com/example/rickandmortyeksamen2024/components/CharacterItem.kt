package com.example.rickandmortyeksamen2024.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.example.rickandmortyeksamen2024.data.Character
import com.example.rickandmortyeksamen2024.data.CreateCharacter

@Composable
fun CharacterItem(character: Character) {
    Column {
        Text(text = character.name)
        Text(text = character.species)
        AsyncImage(
            model = character.image,
            contentDescription = "Image of ${character.name}"
        )
    }
}
