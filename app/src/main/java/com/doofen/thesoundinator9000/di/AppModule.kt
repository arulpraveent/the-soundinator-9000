package com.doofen.thesoundinator9000.di

import android.content.Context
import com.doofen.thesoundinator9000.core.player.ExoPlayerManager
import com.doofen.thesoundinator9000.data.repository.SongRepositoryImpl
import com.doofen.thesoundinator9000.domain.repository.SongRepository
import com.doofen.thesoundinator9000.domain.usecase.GetAllSongsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSongRepository(
        impl: SongRepositoryImpl
    ): SongRepository = impl

    @Provides
    @Singleton
    fun provideGetAllSongsUseCase(
        repo: SongRepository
    ): GetAllSongsUseCase = GetAllSongsUseCase(repo)

    @Provides
    @Singleton
    fun provideExoPlayerManager(
        @ApplicationContext context: Context
    ): ExoPlayerManager {
        return ExoPlayerManager(context)
    }
}