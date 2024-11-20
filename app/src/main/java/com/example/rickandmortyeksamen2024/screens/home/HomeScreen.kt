package com.example.rickandmortyeksamen2024.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navigateToScreen: (String) -> Unit) {

    val viewModel: HomeViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = viewModel.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium)

        )
        Text(
            text = viewModel.title,
            style = TextStyle(
                fontSize = 55.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0, 139, 119)

            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
        )

        // Navigasjons knappene
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
                .padding(bottom = 180.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Navigasjonsknapp til "Vis karakterer"
            Button(
                onClick = { navigateToScreen("show_character") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0, 139, 119))
            ) {
                Text(
                    text = "Vis alle Karakterer",
                    color = Color.White
                )
            }

            // Navigasjonsknapp til "Lage karakter"
            Button(
                onClick = { navigateToScreen("make_character") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0, 139, 119))
            ) {
                Text(
                    text = "Lage Karakter",
                    color = Color.White
                )
            }

            // Navigasjonsknapp til "Dine karakterer"
            Button(
                onClick = { navigateToScreen("your_character") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0, 139, 119))
            ) {
                Text(
                    text = "Dine Karakterer",
                    color = Color.White
                )
            }
        }
    }
}

