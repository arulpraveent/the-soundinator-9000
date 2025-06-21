package com.doofen.thesoundinator9000.presentation.screens.player

import androidx.lifecycle.ViewModel
import com.doofen.thesoundinator9000.domain.model.Song
import com.doofen.thesoundinator9000.player.AudioPlayerManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val audioPlayerManager: AudioPlayerManager
) : ViewModel() {

    fun playSong(song: Song) {
        audioPlayerManager.playSong(song)
    }

    init {
        audioPlayerManager.initialize()
    }
}