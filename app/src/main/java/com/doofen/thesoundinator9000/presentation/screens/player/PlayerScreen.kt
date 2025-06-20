package com.doofen.thesoundinator9000.presentation.screens.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.ui.PlayerView

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val player = remember { viewModel.getPlayer() }

    viewModel.playSong("/storage/emulated/0/Android/media/com.hihonor.medialibrary/_product_h_region_comm_oversea_media_Pre-loaded/Pre-loaded/Music/Go_Beyond_Sonna.mp3")


}