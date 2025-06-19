package com.doofen.thesoundinator9000

import android.content.pm.PackageManager
import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.doofen.thesoundinator9000.presentation.screens.home.HomeScreen
import com.doofen.thesoundinator9000.presentation.theme.TheSoundinator9000Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_AUDIO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                0
            )
        }
        enableEdgeToEdge()
        setContent {
            TheSoundinator9000Theme(
                dynamicColor = false
            ) {
                HomeScreen()
            }
        }
    }
}