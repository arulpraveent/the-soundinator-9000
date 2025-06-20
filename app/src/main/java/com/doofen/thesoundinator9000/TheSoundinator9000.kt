package com.doofen.thesoundinator9000

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TheSoundinator9000 : Application() {

    @Inject
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
        exoPlayer.release()
    }
}