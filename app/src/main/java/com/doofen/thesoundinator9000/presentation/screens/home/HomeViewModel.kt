package com.doofen.thesoundinator9000.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doofen.thesoundinator9000.domain.usecase.GetAllSongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllSongsUseCase: GetAllSongsUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadSongs()
    }

    fun loadSongs(){
        viewModelScope.launch {
            try {
                val songs = getAllSongsUseCase()
                _uiState.value = HomeUiState.Success(songs)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error("Failed to load songs")
            }
        }
    }
}