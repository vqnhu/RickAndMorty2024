package com.example.rickandmortyeksamen2024.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmortyeksamen2024.data.Character


@Composable
fun CharacterItem(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0, 139, 119)) // Bakgrunnsfarge for boksen
            .padding(16.dp), // Innvendig padding for boksen
        //verticalAlignment = Alignment.CenterVertically
    ) {
        // Bilde til venstre
        AsyncImage( // Async laste bilder asynkront, spesielt fra nettverkskilder. Den håndterer innlasting, caching og feil automatisk.
            model = character.image,
            contentDescription = "Image of ${character.name}",
            modifier = Modifier
                .size(84.dp) // Størrelse på bildet
                .clip(RoundedCornerShape(12.dp)) // Gjør bildet firkantet med avrundede hjørner
        )

        Spacer(modifier = Modifier.width(16.dp)) // Plass mellom bildet og teksten

        // Informasjon om karakteren til høyre
        Column {
            Text(text = character.name, fontSize = 20.sp, color = Color.White)
            Text(text = character.species, fontSize = 16.sp, color = Color.White) // Eksempel på ekstra info
        }
    }
    /*Column {
        Text(text = character.name)
        Text(text = character.species)
        AsyncImage(
            model = character.image,
            contentDescription = "Image of ${character.name}"
        )
    }

     */
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun showChar() {
    // Opprett en dummy Character-instans
    val character = Character(
        name = "Rick Sanchez",
        species = "Human",
        image = "picture"
    )

    // Kall CharacterItem med dummy-instansen
    CharacterItem(character)
}
