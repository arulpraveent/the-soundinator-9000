package com.doofen.thesoundinator9000.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.doofen.thesoundinator9000.presentation.components.SongCard
import com.doofen.thesoundinator9000.presentation.screens.player.PlayerViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    playerViewModel: PlayerViewModel,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is HomeUiState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is HomeUiState.Success -> {
            val songs = (state as HomeUiState.Success).songs
            LazyColumn(modifier) {
                items(songs) { song ->
                    SongCard(
                        title = song.title,
                        artist = song.artist
                    )
                }
            }
        }
        is HomeUiState.Error -> {
            Text("Error loading songs", color = Color.Red)
        }
    }
}