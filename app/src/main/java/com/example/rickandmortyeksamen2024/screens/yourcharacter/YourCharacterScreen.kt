package com.example.rickandmortyeksamen2024.screens.yourcharacter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyeksamen2024.components.CharacterItem
import com.example.rickandmortyeksamen2024.components.DeleteCharacterItem
import com.example.rickandmortyeksamen2024.data.Character
import com.example.rickandmortyeksamen2024.data.CreateCharacter
import kotlinx.coroutines.delay

@Composable
fun YourCharacterScreen(yourCharacterViewModel: YourCharacterViewModel) {


    val character = yourCharacterViewModel.characters.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var characterToDelete by remember { mutableStateOf<CreateCharacter?>(null) }
    var deleteAllCharacters by remember { mutableStateOf(false) }


    var message by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        message = true
        delay(3000L)
        yourCharacterViewModel.setCharacters()
        message = false
    }

    Column {
        Text(text = "Dine Rick and Morty karakterer")

        if (message == true) {
            Text("henter karakterene fra databasen")
        }

        LazyColumn {
            items(character.value) { character ->
                DeleteCharacterItem(
                    character,
                    onDelete = {
                        characterToDelete = character
                        showDeleteDialog = true
                    },
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = {
                deleteAllCharacters = true
                showDeleteDialog = true
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color(207, 33, 16, 255))
                ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                Text("Slett alle karakterene", color = Color.White)

            }
        }


        // bekreft sletting av karakter
        if (showDeleteDialog && characterToDelete != null) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text(text = "Bekreft sletting") },
                text = { Text("Er du sikker på at du vil slette ${characterToDelete?.name}?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            characterToDelete?.let { yourCharacterViewModel.deleteCharacter(it) }
                            showDeleteDialog = false
                            characterToDelete = null
                        }
                    ) {
                        Text("Ja")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDeleteDialog = false
                            characterToDelete = null
                        }
                    ) {
                        Text("Nei")
                    }
                }
            )
        }


        // bekreft sletting av ALLE karakter
        if (showDeleteDialog && deleteAllCharacters != null) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text(text = "Bekreft sletting") },
                text = { Text("Er du sikker på at du vil slette ALLE karakterene?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            deleteAllCharacters?.let { yourCharacterViewModel.deleteAllCharacter() }
                            showDeleteDialog = false
                            deleteAllCharacters = false
                        }
                    ) {
                        Text("Ja")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDeleteDialog = false
                            deleteAllCharacters = false
                        }
                    ) {
                        Text("Nei")
                    }
                }
            )
        }
    }
}


//    val characters = yourCharacterViewModel.characters.collectAsState().value
//
//    if (characters.isEmpty()) {
//        Text("No characters available", style = MaterialTheme.typography.bodyLarge)
//
//    } else {
//        LazyColumn {
//            items(characters) { character ->
//                CharacterItem(character = character)
//            }
//        }
//    }
//   }


//@Composable
//fun CharacterItem(character: CreateCharacter) {
//    Column() {
//        Text(text = character.name)
//    }
//
//}
//
//
