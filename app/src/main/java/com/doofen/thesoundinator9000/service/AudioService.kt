package com.doofen.thesoundinator9000.service

import android.content.Intent
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AudioService : MediaSessionService() {

    @Inject
    lateinit var exoPlayer: ExoPlayer

    @Inject
    lateinit var mediaSession: MediaSession

    override fun onCreate() {
        super.onCreate()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)

        if (exoPlayer.isPlaying || exoPlayer.isLoading) {
            exoPlayer.clearMediaItems()
            exoPlayer.clearMediaItems()
        }

        stopSelf()
    }

    override fun onDestroy() {
        mediaSession.run {
            this.player.let {
                it.clearMediaItems()
                it.stop()
            }
            release()
        }

        super.onDestroy()
    }
}