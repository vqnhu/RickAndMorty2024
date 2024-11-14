package com.example.rickandmortyeksamen2024.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

@Serializable
object Home

@Serializable
object ShowCharacter

@Serializable
object MakeCharacter

@Serializable
object YourCharacter

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

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                // navigasjonsskjerm viser forside
                NavigationBarItem(
                    selected = chosenScreen == 0,
                    onClick = {
                        chosenScreen = 0
                        navController.navigate(Home)
                    },
                    icon = {
                        if (chosenScreen == 0) {
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
                        Text(text = "Home")
                    }
                )

                // navigasjon skjerm - vise alle karakterene
                NavigationBarItem(
                    selected = chosenScreen == 1,
                    onClick = {
                        chosenScreen = 1
                        navController.navigate(ShowCharacter)
                    },
                    icon = {
                        if (chosenScreen == 1) {
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
                        Text(text = "All Characters")
                    }
                )

                // navigasjon skjerm - lage karakterer
                NavigationBarItem(
                    selected = chosenScreen == 2,
                    onClick = {
                        chosenScreen = 2
                        navController.navigate(MakeCharacter)
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
                        Text(text = "Make Character")
                    }
                )

                // navigasjon skjerm - vise de nye lagrede karakterene
                NavigationBarItem(
                    selected = chosenScreen == 3,
                    onClick = {
                        chosenScreen = 3
                        navController.navigate(YourCharacter)
                    },
                    icon = {
                        if (chosenScreen == 3) {
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
                        Text(text = "Your Character")
                    }
                )
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
                startDestination = Home
            )
            {
               composable<Home> {
                    HomeScreen(HomeViewModel())
                }
                composable<ShowCharacter> {
                    ShowCharacterScreen(ShowCharacterViewModel())
                }

                composable<MakeCharacter> {
                    MakeNewCharacterScreen(MakeNewCharacterViewModel())
                }

                composable<YourCharacter> {
                    YourCharacterScreen(YourCharacterViewModel())
                }

            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Navigation() {
    val homeViewModel = HomeViewModel()
    val makeNewCharacterViewModel = MakeNewCharacterViewModel()
    val showCharacterViewModel = ShowCharacterViewModel()
    val yourCharacterViewModel = YourCharacterViewModel()
    AppNavigation(
        homeViewModel = homeViewModel,
        makeNewCharacterViewModel = makeNewCharacterViewModel,
        showCharacterViewModel = showCharacterViewModel,
        yourCharacterViewModel = yourCharacterViewModel
    )
}
