package com.doofen.thesoundinator9000.presentation.screens.home

import com.doofen.thesoundinator9000.domain.model.Song

sealed class HomeUiState {
    object Loading: HomeUiState()
    data class Success(val songs: List<Song>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}