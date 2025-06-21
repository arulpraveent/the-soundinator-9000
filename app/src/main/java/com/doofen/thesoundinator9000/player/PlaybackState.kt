package com.doofen.thesoundinator9000.player

import com.doofen.thesoundinator9000.domain.model.Song

data class PlaybackState (
    val currentSong: Song? = null,
    val isPlaying: Boolean = false,
    val currentPositionMs: Long = 0L,
    val totalDurationMs: Long = 0L,
    val isLoading: Boolean = true,
    val error: String? = null
)