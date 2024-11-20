package com.example.rickandmortyeksamen2024.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickandmortyeksamen2024.data.data_classes.CreateCharacter

@Composable
fun CreateCharacterItem(deleteCharacter: CreateCharacter, onDelete: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(
                Color(0, 139, 119),
                shape = RoundedCornerShape(16.dp)
            )
            .border(2.dp, Color(195, 214, 0), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Bilde av karakteren
        Image(
            painter = painterResource(id = deleteCharacter.image),
            contentDescription = "${deleteCharacter.name} Image",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(24.dp)) // "klipper" bildet og gjør den rund
        )

        // Informasjons boks
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f)
        ) {
            Text(
                "Navn: ${deleteCharacter.name}",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(195, 214, 0),
            )
            Text(
                "Type: ${deleteCharacter.species}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                "Status: ${deleteCharacter.status}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }


        // Knapp for å slette karakter
        if (onDelete != null) {
            IconButton(
                onClick = onDelete,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        Color(216, 66, 66, 255),
                        CircleShape
                    )
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete ${deleteCharacter.name}",
                    tint = Color.White
                )
            }
        }
    }
}
