package com.doofen.thesoundinator9000.data.source

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.doofen.thesoundinator9000.domain.model.Song
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MediaStoreDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun fetchAllSongs(): List<Song> {
        val songList = mutableListOf<Song>()

        val collection =
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

        context.contentResolver.query(
            collection,
            projection,
            selection,
            null,
            sortOrder
        )?.use { cursor ->
            val idCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val dataCol = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idCol)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                val song = Song(
                    id = id,
                    title = cursor.getString(titleCol) ?: "Unknown",
                    artist = cursor.getString(artistCol) ?: "Unknown Artist",
                    contentUri = contentUri,
                    filePath = dataCol.let { cursor.getString(it) }
                )
                songList.add(song)
            }
        }

        return songList
    }
}