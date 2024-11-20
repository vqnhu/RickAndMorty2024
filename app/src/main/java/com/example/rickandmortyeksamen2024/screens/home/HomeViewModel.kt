package com.example.rickandmortyeksamen2024.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rickandmortyeksamen2024.R

class HomeViewModel : ViewModel() {

    var title by mutableStateOf("Rick and Morty")
        private set

    var image = R.drawable.ricknmortyy

}