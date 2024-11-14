package com.example.rickandmortyeksamen2024.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.rickandmortyeksamen2024.data.CreateCharacter

@Composable
fun DeleteCharacterItem(deleteCharacter: CreateCharacter, onDelete: (() -> Unit)? = null) {
    Column {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text(deleteCharacter.name)
            Text(deleteCharacter.species)
            Text(deleteCharacter.status)
            Text(deleteCharacter.image)
            if (onDelete != null) {
                IconButton(onClick = { onDelete() }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }


        }
    }

}