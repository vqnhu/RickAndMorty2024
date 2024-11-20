package com.example.rickandmortyeksamen2024.screens.showcharacter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.rickandmortyeksamen2024.components.CharacterItem
import kotlinx.coroutines.delay

@Composable
fun ShowCharacterScreen(showCharacterViewModel: ShowCharacterViewModel) {

    //  koble til screen og ViewModel
    val showCharacters by showCharacterViewModel.showCharacters.collectAsState()
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isLoading = true
        delay(2000)
        isLoading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(13, 56, 52, 255))

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rick and Morty karakterer",
                fontSize = 30.sp,
                color = Color(195, 214, 0),
            )

            // Venter til "LaucheedEffect" er ferdig så viser karakterene
            if (isLoading) {
                Text("Laster inn karakerene....")
            } else {

                LazyColumn {
                    items(showCharacters) { character ->
                        CharacterItem(character)
                    }
                }
            }


        }
    }


}



