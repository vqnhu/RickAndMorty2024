import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortyeksamen2024.data.data_classes.CreateCharacter

@Composable
fun DeleteCharacterDialog(
    characterToDelete: CreateCharacter?,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (characterToDelete != null) {
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
                    "Er du sikker på at du vil slette ${characterToDelete.name}?",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("Ja", color = Color(0,0,0,))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Nei", color = Color(0,0,0))
                }
            },
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.large,
            containerColor = Color(187, 250, 244, 255)
        )
    }
}