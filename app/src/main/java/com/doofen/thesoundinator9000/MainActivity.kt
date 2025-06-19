package com.doofen.thesoundinator9000

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doofen.thesoundinator9000.presentation.screens.home.HomeScreen
import com.doofen.thesoundinator9000.presentation.theme.TheSoundinator9000Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

@Composable
fun SoundinatorMainPage() {
    Scaffold(
        topBar = {
            SoundinatorTobBar()
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SoundinatorTobBar(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .heightIn(max = 20.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.the_soundinator_9000_icon),
            contentDescription = ""
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheSoundinator9000Theme {
        SoundinatorMainPage()
    }
}