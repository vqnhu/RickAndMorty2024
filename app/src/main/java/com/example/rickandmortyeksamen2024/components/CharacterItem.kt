package com.example.rickandmortyeksamen2024.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmortyeksamen2024.data.Character


@Composable
fun CharacterItem(character: Character) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                Color(0, 139, 119),
                shape = RoundedCornerShape(16.dp)
            ) // Bakgrunnsfarge for boksen
            .border(4.dp, Color(195, 214, 0), RoundedCornerShape(16.dp)) // lager en border rundt kortet og styler den gul
            .padding(16.dp), // Innvendig padding for boksen

    ) {
        // inne i kortet er det et Column for å ordne innholdet vertikalt
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
            ) {
            // Bilde til venstre
            AsyncImage( // Async laste bilder asynkront, spesielt fra nettverkskilder. Den håndterer innlasting, caching og feil automatisk.
                model = character.image,
                contentDescription = "Image of ${character.name}",
                modifier = Modifier
                    .size(130.dp) // Størrelse på bildet
                    .clip(RoundedCornerShape(50.dp)) // "klipper" bildet og gjør bildet rund

            )

            Spacer(modifier = Modifier.width(8.dp)) // Plass mellom bildet og teksten

            // Informasjon om karakteren til høyre

                Text(
                    text = character.name,
                    fontSize = 24.sp,
                    color = Color(195, 214, 0),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = character.species,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            Text(
                text = character.status,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

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

