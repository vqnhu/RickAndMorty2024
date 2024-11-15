package com.example.rickandmortyeksamen2024.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickandmortyeksamen2024.data.CreateCharacter

@Composable
fun DeleteCharacterItem(deleteCharacter: CreateCharacter, onDelete: (() -> Unit)? = null) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Name: ${deleteCharacter.name}")
                Text(text = "Species: ${deleteCharacter.species}")
                Text(text = "Status: ${deleteCharacter.status}")
                Text(text = "Image: ${deleteCharacter.image}")
            }

            if (onDelete != null) {
                IconButton(onClick = { onDelete() }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Character"
                    )
                }
            }
        }
    }
}
