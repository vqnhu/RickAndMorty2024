package com.example.rickandmortyeksamen2024.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.rickandmortyeksamen2024.data.CreateCharacter

@Composable
fun DeleteCharacterItem(deleteCharacter: CreateCharacter, onDelete: (() -> Unit)? = null) {
    Column {
        Text(deleteCharacter.name)
        if (onDelete != null) {
            IconButton(onClick = { onDelete() }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
            }
        }
    }
}