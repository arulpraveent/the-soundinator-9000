package com.doofen.thesoundinator9000.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object AudioServiceModule {

    @Provides
    @ServiceScoped
    fun provideMediaSession (
        @ApplicationContext context: Context,
        exoPlayer: ExoPlayer
    ) : MediaSession = MediaSession.Builder(context, exoPlayer)
        .build()
}