package com.doofen.thesoundinator9000.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

@Composable
inline fun <reified VM : ViewModel> sharedViewModel(
    navController: NavHostController,
    parentRoute: String
): VM {
    val parentEntry = rememberUpdatedState(
        navController.getBackStackEntry(parentRoute)
    ).value
    return hiltViewModel(parentEntry)
}