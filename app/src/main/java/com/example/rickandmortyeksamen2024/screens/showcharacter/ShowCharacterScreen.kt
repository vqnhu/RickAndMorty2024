package com.example.rickandmortyeksamen2024.screens.showcharacter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.Style
import com.example.rickandmortyeksamen2024.components.CharacterItem
import com.example.rickandmortyeksamen2024.data.Character
import com.example.rickandmortyeksamen2024.data.RickAndMortyRepository
import kotlinx.coroutines.launch

@Composable
fun ShowCharacterScreen(showCharacterViewModel: ShowCharacterViewModel) {

    // 1. koble til screen og ViewModel
    val showCharacters = showCharacterViewModel.showCharacters.collectAsState()

    // 2. pakke med ting vi må importere
    var characters by remember {
        mutableStateOf<List<Character>>(emptyList())
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            characters = RickAndMortyRepository.getAllCharacters()
        }
    }

    // 3. jobbe med grensesnitt
    Box( // denne boksen endrer bakgrunnfarge på skjermen
        modifier = Modifier
            .fillMaxSize()
            .background(Color(13, 56, 52, 255))

    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rick and Morty karakterer",
                fontSize = 30.sp,
                color = Color(195, 214, 0),
                )



            // Bruker LazyColumn for å liste karakterene
            LazyColumn {
                items(characters) { showCharacter ->
                    CharacterItem(showCharacter) // Passer karakteren til en egen komponent
                }
            }

        }
    }


}



