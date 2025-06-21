package com.doofen.thesoundinator9000.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.doofen.thesoundinator9000.R
import com.doofen.thesoundinator9000.presentation.navigation.MainNavGraph
import com.doofen.thesoundinator9000.presentation.navigation.sharedViewModel
import com.doofen.thesoundinator9000.presentation.screens.home.HomeScreen
import com.doofen.thesoundinator9000.presentation.screens.player.PlayerScreen
import com.doofen.thesoundinator9000.presentation.screens.player.PlayerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        modifier = Modifier
            .imePadding()
            .windowInsetsPadding(
                WindowInsets.systemBars
            ),
        topBar = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Soundinator",
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        content = { innerPadding ->
            NavHost(navController = navController, startDestination = "home_screen", route = "root") {
                composable(route = MainNavGraph.Home.route) {
                    val playerViewModel: PlayerViewModel = sharedViewModel(navController,"root")
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        playerViewModel = playerViewModel
                    )
                }
                composable(route = MainNavGraph.Player.route) {
                    val playerViewModel = hiltViewModel<PlayerViewModel>(
                        navController.getBackStackEntry("root")
                    )
                    PlayerScreen()
                }
            }
        },
        bottomBar = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavigationBar {
                    NavigationBarItem (
                        selected = currentRoute === MainNavGraph.Home.route,
                        onClick = {
                            navController.navigate(MainNavGraph.Home.route)
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = stringResource(R.string.home_icon_desc)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.home_icon_label),
                                style = MaterialTheme.typography.labelMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )

                    NavigationBarItem (
                        selected = currentRoute === MainNavGraph.Player.route,
                        onClick = {
                            navController.navigate(MainNavGraph.Player.route)
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = stringResource(R.string.home_icon_desc)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.home_icon_label),
                                style = MaterialTheme.typography.labelMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        }
    )
}