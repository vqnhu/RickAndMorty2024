package com.example.rickandmortyeksamen2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.rickandmortyeksamen2024.data.room.CharacterRepository
import com.example.rickandmortyeksamen2024.navigation.AppNavigation
import com.example.rickandmortyeksamen2024.screens.home.HomeViewModel
import com.example.rickandmortyeksamen2024.screens.makenewcharacter.MakeNewCharacterViewModel
import com.example.rickandmortyeksamen2024.screens.showcharacter.ShowCharacterViewModel
import com.example.rickandmortyeksamen2024.screens.yourcharacter.YourCharacterViewModel
import com.example.rickandmortyeksamen2024.ui.theme.RickAndMortyEksamen2024Theme

class MainActivity : ComponentActivity() {

    private val _homeViewModel: HomeViewModel by viewModels()
    private val _makeNewCharacterViewModel: MakeNewCharacterViewModel by viewModels()
    private val _showCharacterViewModel: ShowCharacterViewModel by viewModels()
    private val _yourCharacterViewModel: YourCharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisere databasen
        CharacterRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            RickAndMortyEksamen2024Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation(
                            _homeViewModel,
                            _makeNewCharacterViewModel,
                            _showCharacterViewModel,
                            _yourCharacterViewModel
                        )
                    }
                }
            }
        }
    }
}





