package com.example.rickandmortyeksamen2024.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortyeksamen2024.navigation.AppNavigation

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val viewModel: HomeViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = viewModel.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()

        )
        Text(
            text = viewModel.title,
            style = TextStyle(
                fontSize = 55.sp,
                textAlign = TextAlign.Center,
                color = Color(0, 139, 119)

            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp)
        )
        Button(
            onClick = {
                // TODO:
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0, 139, 119), // bakgrunnsfarge
                contentColor = Color.Yellow

            ),
            modifier = Modifier
                .padding(top = 260.dp)
                .padding(horizontal = 160.dp)

        ) {
            Text(text = "START", fontSize = 12.sp)

        }

    }
}

