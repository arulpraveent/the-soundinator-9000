package com.doofen.thesoundinator9000.presentation.navigation

sealed class MainNavGraph(val route: String) {
    object Home : MainNavGraph("home_screen")
    object Player : MainNavGraph("player_screen")
    object Settings : MainNavGraph("settings_screen")
}