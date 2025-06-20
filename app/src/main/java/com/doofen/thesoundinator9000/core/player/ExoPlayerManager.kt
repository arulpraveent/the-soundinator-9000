package com.doofen.thesoundinator9000.core.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExoPlayerManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val exoPlayer by lazy {
        ExoPlayer.Builder(context).build()
    }

    fun playFromUri(uri: String) {
        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer.addMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun release() {
        exoPlayer.release()
    }

    fun getPlayer() : ExoPlayer = exoPlayer
}