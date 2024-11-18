package com.example.rickandmortyeksamen2024.screens.yourcharacter

import DeleteCharacterDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyeksamen2024.components.CreateCharacterItem
import com.example.rickandmortyeksamen2024.data.CreateCharacter
import com.example.rickandmortyeksamen2024.deleteallcharactersdialog.DeleteAllCharactersDialog

@Composable
fun YourCharacterScreen(yourCharacterViewModel: YourCharacterViewModel) {


    val character = yourCharacterViewModel.characters.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDeleteAllDialog by remember { mutableStateOf(false) }
    var characterToDelete by remember { mutableStateOf<CreateCharacter?>(null) }
    var deleteAllCharacters by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(13, 56, 52, 255))
        ) {
            Text(
                text = "Dine karakterer",
                color = Color(195, 214, 0),
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center

                )

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                items(character.value, key = { it.id }) { character ->
                    CreateCharacterItem(
                        character,
                        onDelete = {
                            characterToDelete = character
                            showDeleteDialog = true
                        },
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center

            ) {
                Button(
                    onClick = {
                        showDeleteAllDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(50.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(207, 33, 16, 255))
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Slett alle karakterene", color = Color.White)

                }
            }

            // dialog for å slette en karakter
            if (showDeleteDialog) {
                DeleteCharacterDialog(
                    characterToDelete = characterToDelete,
                    onConfirm = {
                        characterToDelete?.let { yourCharacterViewModel.deleteCharacter(it) }
                        showDeleteDialog = false
                        characterToDelete = null
                    },
                    onDismiss = {
                        showDeleteDialog = false
                        characterToDelete = null
                    }
                )
            }

            // dialog for å slette alle karakterene
            if (showDeleteAllDialog) {
                DeleteAllCharactersDialog(
                    onConfirm = {
                        yourCharacterViewModel.deleteAllCharacter()
                        showDeleteAllDialog = false
                    },
                    onDismiss = { showDeleteAllDialog = false }
                )
            }

        }
    }
}

