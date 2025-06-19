package com.doofen.thesoundinator9000.domain.repository

import com.doofen.thesoundinator9000.domain.model.Song

interface SongRepository {
    suspend fun  getAllSongs(): List<Song>
}