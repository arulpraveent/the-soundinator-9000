package com.doofen.thesoundinator9000.player

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.doofen.thesoundinator9000.domain.model.Song
import com.doofen.thesoundinator9000.service.AudioService
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioPlayerManager @Inject constructor (
    @ApplicationContext private val context: Context,
) : AudioPlayerController, Player.Listener {

    private val _playbackState : MutableStateFlow<PlaybackState> = MutableStateFlow(PlaybackState())

    override val playbackState = _playbackState.asStateFlow()

    private var mediaControllerFuture : ListenableFuture<MediaController>? = null
    private var mediaController : MediaController? = null

    override fun initialize() {
        if (mediaController != null || mediaControllerFuture?.isDone == false || mediaControllerFuture?.isCancelled == false) {
            return
        }

        val sessionToken = SessionToken(context, ComponentName(context, AudioService::class.java))
        mediaControllerFuture = MediaController.Builder(context, sessionToken).buildAsync()

        mediaControllerFuture?.addListener (
            Runnable {
                try {
                    mediaController = mediaControllerFuture?.get()
                    mediaController?.addListener(this)

                    _playbackState.value = _playbackState.value.copy(isLoading = false)
                } catch (e : Exception) {
                    println("failed to get audio service")
                    _playbackState.value = _playbackState.value.copy(
                        isLoading = false,
                        error = "Oh no! Failed to connect to media service: ${e.message}"
                    )
                }
            },
            MoreExecutors.directExecutor()
        )
    }

    override fun playSong(song: Song) {
        println("preping to play song /////////////////")
        mediaController?.let { mc ->
            println("preping to play song through media controller /////////////////")
            mc.addMediaItem(song.toMediaItem())
            mc.prepare()
            mc.playWhenReady = true
            mc.play()
        }
    }

    override fun addSong(song: Song) {
        mediaController?.let { mc ->
            if (_playbackState.value.isPlaying) {
                mediaController?.addMediaItem(song.toMediaItem())
            }
        }
    }

    private fun Song.toMediaItem () : MediaItem {
        return MediaItem.fromUri(this.filePath)
    }

    override fun seekToNext() {
        mediaController?.seekToNextMediaItem()
    }

    override fun seekToPrevious() {
        mediaController?.seekToPreviousMediaItem()
    }

    override fun seekTo(durationMs: Long) {
        mediaController?.seekTo(durationMs)
    }

    override fun pause() {
        mediaController?.pause()
    }

    override fun stop() {
        mediaController?.clearMediaItems()
        mediaController?.stop()
    }


}