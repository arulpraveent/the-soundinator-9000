package com.doofen.thesoundinator9000.di

import android.content.Context
import androidx.activity.contextaware.ContextAware
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import com.doofen.thesoundinator9000.player.AudioPlayerManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AudioPlayerModule {

    @Provides
    @Singleton
    fun audioAttributeProvider() : AudioAttributes = AudioAttributes.Builder()
        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @Provides
    @Singleton
    fun provideExoPlayer(
        @ApplicationContext context : Context,
        audioAttributes: AudioAttributes
    ) : ExoPlayer = ExoPlayer.Builder(context)
        .setAudioAttributes(audioAttributes, true)
        .setHandleAudioBecomingNoisy(true)
        .setWakeMode(C.WAKE_MODE_LOCAL)
        .build()

    @Provides
    @Singleton
    fun provideAudioPlayerManager (
        @ApplicationContext context: Context
    ) : AudioPlayerManager = AudioPlayerManager(context = context)
}