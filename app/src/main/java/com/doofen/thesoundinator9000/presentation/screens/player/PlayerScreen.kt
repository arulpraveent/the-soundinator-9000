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

    viewModel.playSong("content://media/external/audio/media/1000000024")

    AndroidView(
        factory = {
            PlayerView(context).apply {
                useController = true
                setPlayer(player)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}