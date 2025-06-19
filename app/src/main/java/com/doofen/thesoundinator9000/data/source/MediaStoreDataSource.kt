package com.doofen.thesoundinator9000.data.source

import android.content.Context
import android.provider.MediaStore
import com.doofen.thesoundinator9000.domain.model.Song
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MediaStoreDataSource @Inject constructor(
    @ApplicationContext private val context: Context
){
    fun fetchAllSongs(): List<Song>{
        val songList = mutableListOf<Song>()

        val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val cursor = context.contentResolver.query(collection, projection, selection, null, null)

        cursor?.use {
            val idCol = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleCol = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistCol = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val dataCol = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (it.moveToNext()) {
                val song = Song(
                    id = it.getLong(idCol),
                    title = it.getString(titleCol),
                    artist = it.getString(artistCol),
                    path = it.getString(dataCol)
                )
                songList.add(song)
            }
        }

        return songList
    }
}