package com.doofen.thesoundinator9000.data.repository

import com.doofen.thesoundinator9000.data.source.MediaStoreDataSource
import com.doofen.thesoundinator9000.domain.model.Song
import com.doofen.thesoundinator9000.domain.repository.SongRepository
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor (
    private val mediaStoreDataSource: MediaStoreDataSource
) : SongRepository {
    override suspend fun getAllSongs(): List<Song> {
        return mediaStoreDataSource.fetchAllSongs()
    }
}