package com.example.rickandmortyeksamen2024.deleteallcharactersdialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun DeleteAllCharactersDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Bekreft sletting",
                style = MaterialTheme.typography.headlineMedium
            )
        },
        text = {
            Text(
                "Er du sikker på at du vil slette ALLE karakterene?",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Ja", color = Color(0,0,0)) // Green for confirmation
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Nei", color = Color(0,0,0,)) // Neutral for cancel
            }
        },
        modifier = Modifier.padding(16.dp),
        shape = MaterialTheme.shapes.large,
        containerColor = Color(187, 250, 244, 255)
    )
}