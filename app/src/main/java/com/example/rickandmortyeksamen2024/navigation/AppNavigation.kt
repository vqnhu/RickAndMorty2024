package com.example.rickandmortyeksamen2024.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyeksamen2024.screens.home.HomeScreen
import com.example.rickandmortyeksamen2024.screens.home.HomeViewModel
import com.example.rickandmortyeksamen2024.screens.makenewcharacter.MakeNewCharacterScreen
import com.example.rickandmortyeksamen2024.screens.makenewcharacter.MakeNewCharacterViewModel
import com.example.rickandmortyeksamen2024.screens.showcharacter.ShowCharacterScreen
import com.example.rickandmortyeksamen2024.screens.showcharacter.ShowCharacterViewModel
import com.example.rickandmortyeksamen2024.screens.yourcharacter.YourCharacterScreen
import com.example.rickandmortyeksamen2024.screens.yourcharacter.YourCharacterViewModel
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    homeViewModel: HomeViewModel,
    makeNewCharacterViewModel: MakeNewCharacterViewModel,
    showCharacterViewModel: ShowCharacterViewModel,
    yourCharacterViewModel: YourCharacterViewModel
) {
    val navController = rememberNavController()
    var chosenScreen by remember {
        mutableStateOf(0)
    }

    // Henter den nåværendelen av skjermen
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    // Henter inn hvilket skjetm som er valgt
    val currentDestination = currentBackStackEntry?.destination?.route

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // Hvis nåværende skjerm ikke er hjem -> vis navigasjonsbaren
            if (currentDestination != "home") {
                NavigationBar(
                    containerColor = Color(0, 150, 136, 255),
                    contentColor = Color.White
                ) {
                    // Skjerm -> Hjem
                    NavigationBarItem(
                        selected = currentDestination == "home",
                        onClick = {
                            navController.navigate("home")
                        },
                        icon = {
                            if (currentDestination == "home") {
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.Home,
                                    contentDescription = null
                                )
                            }
                        },
                        label = {
                            Text(text = "Hjem")
                        }
                    )

                    // Skjerm - Vise alle karakterene
                    NavigationBarItem(
                        selected = chosenScreen == 1,
                        onClick = {
                            chosenScreen = 1
                            navController.navigate("show_character")
                        },
                        icon = {
                            if (chosenScreen == 1) {
                                Icon(
                                    imageVector = Icons.Filled.Face,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.Face,
                                    contentDescription = null
                                )
                            }
                        },
                        label = {
                            Text(text = "Alle karakterer")
                        }
                    )

                    // Skjerm - Lage karakter
                    NavigationBarItem(
                        selected = chosenScreen == 2,
                        onClick = {
                            chosenScreen = 2
                            navController.navigate("make_character")
                        },
                        icon = {
                            if (chosenScreen == 2) {
                                Icon(
                                    imageVector = Icons.Filled.Create,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.Create,
                                    contentDescription = null
                                )
                            }

                        },
                        label = {
                            Text(text = "Lage karakterer")
                        }
                    )

                    // Skjerm - Dine karakterer
                    NavigationBarItem(
                        selected = chosenScreen == 3,
                        onClick = {
                            chosenScreen = 3
                            navController.navigate("your_character")
                        },
                        icon = {
                            if (chosenScreen == 3) {
                                Icon(
                                    imageVector = Icons.Filled.Build,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.Build,
                                    contentDescription = null
                                )
                            }
                        },
                        label = {
                            Text(text = "Dine karakterer")
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            NavHost(
                navController = navController,
                startDestination = "home"
            )
            {
                composable("home") {
                    HomeScreen(navigateToScreen = { screen -> navController.navigate(screen) })
                }
                composable("show_character") {
                    ShowCharacterScreen(ShowCharacterViewModel())
                }

                composable("make_character") {
                    MakeNewCharacterScreen(MakeNewCharacterViewModel())
                }

                composable("your_character") {
                    YourCharacterScreen(YourCharacterViewModel())
                }
            }
        }
    }
}
