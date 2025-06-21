package com.doofen.thesoundinator9000.player

import com.doofen.thesoundinator9000.domain.model.Song
import kotlinx.coroutines.flow.StateFlow

interface AudioPlayerController {

    val playbackState : StateFlow<PlaybackState>

    fun initialize ()

    fun playSong (song: Song)

    fun addSong (song: Song)

    fun seekToNext ()

    fun seekToPrevious ()

    fun seekTo (durationMs : Long)

    fun pause ()

    fun stop ()
}