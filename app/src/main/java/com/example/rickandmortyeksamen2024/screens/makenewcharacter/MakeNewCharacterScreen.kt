package com.example.rickandmortyeksamen2024.screens.makenewcharacter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortyeksamen2024.R
import com.example.rickandmortyeksamen2024.data.CreateCharacter
import kotlinx.coroutines.delay

@Composable
fun MakeNewCharacterScreen(makeNewCharacterViewModel: MakeNewCharacterViewModel) {

    val characters = makeNewCharacterViewModel.characters.collectAsState()

    var name by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    // Meldingen til brukeren om at karakteren er lagret
    var message by remember { mutableStateOf(false) }

    val images = listOf(
        R.drawable.calypso,
        R.drawable.noob,
       /* R.drawable.diane,
        R.drawable.dimension,
        R.drawable.lady_katana,
        R.drawable.mr_frundles,
        R.drawable.poopy_butthole,

        */
    )

    var selectedImages by remember { mutableStateOf(setOf<Int>()) }

    Column {
        Text(text = "Lag din Rick and Morty karakter her: ")

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Navn") }
        )
        TextField(
            value = species,
            onValueChange = { species = it },
            label = { Text(text = "Type") }
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = (status == "Alive"),
                onClick = { status = "Alive" })
            Text(text = "Alive")
            RadioButton(
                selected = (status == "Dead"),
                onClick = { status = "Dead" })
            Text(text = "Dead")

        }


        images.forEach { image ->
            val isSelected = selectedImages.contains(image)
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
                    .clickable {
                        // Toggle the image selection
                        selectedImages = if (isSelected) {
                            selectedImages - image
                        } else {
                            selectedImages + image
                        }
                    }
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Character Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(if (isSelected) 0.8f else 1f) // Scale down when selected
                )

                // Show check mark if the image is selected
                if (isSelected) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "Selected",
                        tint = Color.Green,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    )
                }
            }
        }


        Button(onClick = {
            val newCharacter =
                CreateCharacter(
                    name = name,
                    species = species,
                    status = status,
                    image = selectedImages.toString()
                )
            makeNewCharacterViewModel.insertCharachter(newCharacter)
            name = ""
            species = ""
            status = ""
            message = true
        })

        {
            Text(text = "Lagre karakter i databasen")
        }

        AnimatedVisibility(
            visible = message,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Row(
                modifier = Modifier
                    .background(color = Color(0, 128, 0))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "Success",
                    tint = Color.White
                )
                Text(
                    text = "Karakter lagret til dine karakterer",
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)

                )
            }
        }


        if (message == true) {
            LaunchedEffect(Unit) {
                delay(1500L)
                message = false
            }
        }


        /* LazyColumn {
             items(characters.value) { character ->
                 Text(text = "Navn:  ${character.name}")
             }
         }

         */
    }
}