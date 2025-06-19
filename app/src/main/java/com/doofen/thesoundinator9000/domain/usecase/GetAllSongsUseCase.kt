package com.doofen.thesoundinator9000.domain.usecase

import com.doofen.thesoundinator9000.domain.repository.SongRepository
import javax.inject.Inject

class GetAllSongsUseCase @Inject constructor(private val repository: SongRepository){
    suspend operator fun invoke() = repository.getAllSongs()
}