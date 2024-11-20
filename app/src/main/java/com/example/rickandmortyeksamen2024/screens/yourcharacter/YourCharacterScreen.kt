package com.example.rickandmortyeksamen2024.screens.yourcharacter

import DeleteCharacterDialog
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyeksamen2024.components.CreateCharacterItem
import com.example.rickandmortyeksamen2024.data.data_classes.CreateCharacter
import com.example.rickandmortyeksamen2024.dialog.DeleteAllCharactersDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun YourCharacterScreen(yourCharacterViewModel: YourCharacterViewModel) {

    // Tilstander
    val characters = yourCharacterViewModel.characters.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showDeleteAllDialog by remember { mutableStateOf(false) }
    var characterToDelete by remember { mutableStateOf<CreateCharacter?>(null) }
    var message by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    // simulere lasting av data
    LaunchedEffect(Unit) {
        isLoading = true
        delay(3000L)
        isLoading = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(13, 56, 52, 255))
        ) {
            // Tittel
            Text(
                text = "Dine karakterer",
                color = Color(195, 214, 0),
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center

            )

            // Vis denne meldingen "Laster inn karakterene..." mens "LauchedEffect" pågår
            if (isLoading) {
                Text("Laster inn karakterene...")
            } else {

                // Hvis ingen karakterer er funnet vis "Ingen karakter er tilgjengelig"
                if (characters.value.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ingen karakterer er tilgjengelig",
                            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
                        )
                    }

                }

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    // Key brukes for å gi hvert element et unikt nøkkel
                    items(characters.value, key = { it.id }) { character ->
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
                    // Knapp for å slette ALLE karakterene
                    Button(
                        onClick = {
                            showDeleteAllDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(50.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                207,
                                33,
                                16,
                                255
                            )
                        ),
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
            }
        }
        // Dialog for å slette EN karakter
        if (showDeleteDialog) {
            DeleteCharacterDialog(
                characterToDelete = characterToDelete,
                onConfirm = {
                    characterToDelete?.let {
                        yourCharacterViewModel.deleteCharacter(it)
                        coroutineScope.launch {
                            message = "${it.name} ble slettet!"
                            delay(2000) // Vises meldingen i 2 sek.
                            message = ""
                        }
                    }
                    showDeleteDialog = false
                    characterToDelete = null
                },
                onDismiss = {
                    showDeleteDialog = false
                    characterToDelete = null
                }
            )
        }

        // Dialog for å slette ALLE karakterene
        if (showDeleteAllDialog) {
            DeleteAllCharactersDialog(
                onConfirm = {
                    yourCharacterViewModel.deleteAllCharacters()
                    coroutineScope.launch {
                        message = "Alle karakterene ble slettet!"
                        delay(2000) // Vises meldingen i 2 sek.
                        message = ""
                    }
                    showDeleteAllDialog = false
                },
                onDismiss = { showDeleteAllDialog = false }
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {

            // Bekreftelses melding
            if (message.isNotEmpty()) {
                androidx.compose.animation.AnimatedVisibility(
                    visible = message.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0, 0, 0, 107))
                            .align(Alignment.Center)
                    ) {
                        Row(
                            modifier = Modifier
                                .background(
                                    color = Color(13, 56, 52, 255),
                                    shape = MaterialTheme.shapes.medium
                                )
                                .padding(16.dp)
                                .align(Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Success",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp)) // Spacer for å lage mellomrom mellom ikon og tekst
                            Text(
                                text = message,
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

