package com.doofen.thesoundinator9000.presentation.screens.player

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.doofen.thesoundinator9000.data.player.ExoPlayerManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val exoPlayerManager: ExoPlayerManager
) : ViewModel() {
    fun playSong(path: String) {
        exoPlayerManager.playFromUri(path)
    }

    fun pause() {
        exoPlayerManager.pause()
    }

    fun getPlayer(): ExoPlayer = exoPlayerManager.getPlayer()

    fun release() {
        exoPlayerManager.release()
    }
}