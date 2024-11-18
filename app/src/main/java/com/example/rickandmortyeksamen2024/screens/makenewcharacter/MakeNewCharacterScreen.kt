package com.example.rickandmortyeksamen2024.screens.makenewcharacter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyeksamen2024.R
import com.example.rickandmortyeksamen2024.data.CreateCharacter
import kotlinx.coroutines.delay

@Composable
fun MakeNewCharacterScreen(makeNewCharacterViewModel: MakeNewCharacterViewModel) {

    var name by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<Int?>(null) }
    var message by remember { mutableStateOf(false) } // Meldingen til brukeren om at karakteren er lagret

    // Error handling for feltene 
    var nameError by remember { mutableStateOf(false) }
    var speciesError by remember { mutableStateOf(false) }
    var statusError by remember { mutableStateOf(false) }
    var imageError by remember { mutableStateOf(false) }

    // for å scrolle på siden
    val scrollState = rememberScrollState()

    // Bilder av karakterer brukeren kan velge
    val images = listOf(
        R.drawable.calypso,
        R.drawable.noob,
        R.drawable.diane,
        R.drawable.dimension,
        R.drawable.lady_katana,
        R.drawable.mr_frundles,
        R.drawable.poopy_butthole,
        R.drawable.fart,
        R.drawable.drippyboy,
        R.drawable.elle,
        R.drawable.beebo,
        R.drawable.fat_rick,
        R.drawable.fish_morty,
        R.drawable.daphne,
        R.drawable.santa_earth

    )


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color(31, 31, 34, 255))
                .padding(8.dp)
        ) {
            Text(
                text = "Lag din egen karakter",
                color = Color(195, 214, 0),
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            TextField(
                value = name,
                onValueChange = // det bruker taster inn
                {
                    name = it
                    nameError = it.isBlank() // Blir "true" hvis feltet er tomt
                },
                label = { Text("Navn") },
                isError = nameError, // Behandler Error

            )

            if (nameError) {
                Text(
                    text = "Navn er påkrevd",
                    color = Color.Red,
                )
            }


            TextField(
                value = species,
                onValueChange =
                {
                    species = it
                    speciesError = it.isBlank()
                },
                label = { Text(text = "Type") },
                isError = speciesError,
                modifier = Modifier.padding(top = 8.dp)

            )

            if (speciesError) {
                Text(
                    text = "Type er påkrevd",
                    color = Color.Red,
                )
            }

            Text(
                text = "Status: ",
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (status == "Alive"),
                    onClick = {
                        status = "Alive"
                        statusError = false
                    },
                colors = RadioButtonDefaults.colors(selectedColor = Color.Green)
                )
                Text(text = "Alive", modifier = Modifier.padding(8.dp), color = Color.White)

                RadioButton(
                    selected = (status == "Dead"),
                    onClick = {
                        status = "Dead"
                        statusError = false
                    },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
                )
                Text(text = "Dead", color = Color.White)

            }

            if (statusError) {
                Text(
                    text = "Status er påkrevd",
                    color = Color.Red
                )
            }

            // Bildevalg av nye karakterer
            Text(
                text = "Velg et bilde",
                color = Color.White
            )

            // Plasserer 3 bilder på hvert rad vertikalt
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(400.dp)

                ) {
                // items for å itirere gjennom bildene
                items(images) { image ->
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clickable {
                                selectedImage = if (selectedImage == image) null else image
                            }
                    )
                    {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "Karakter bilde",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                        // if-en gjør at når bildet er valgt kommer det en "check" ikon på høyre top hjørne
                        if (selectedImage == image) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = "Valgt",
                                tint = Color.Green,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(24.dp)
                            )
                        }
                    }
                }
            }

            if (imageError) {
                Text(
                    text = "Bildevalg er påkrevd",
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(50.dp)) // mellomrom mellom bildene og knappen

            // Lagre knappen
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(13, 56, 52, 255)),
                onClick = {
                    nameError = name.isBlank()
                    speciesError = species.isBlank()
                    statusError = status.isBlank()
                    imageError = (selectedImage == null)

                    if (nameError || speciesError || statusError || imageError) {
                        return@Button
                    }

                    val newCharacter = CreateCharacter(
                        name = name,
                        species = species,
                        status = status,
                        image = selectedImage!! // legg inn kommentar
                    )
                    makeNewCharacterViewModel.insertCharachter(newCharacter)
                    name = ""
                    species = ""
                    status = ""
                    selectedImage = null
                    message = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            {
                Text(text = "Lagre karakter")

            }
        }

        AnimatedVisibility(
            visible = message,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(102, 0, 0, 5))
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .padding(16.dp)
                        .align(Alignment.Center),
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
        }

        if (message == true) {
            LaunchedEffect(Unit) {
                delay(1500L)
                message = false
            }
        }
    }
}