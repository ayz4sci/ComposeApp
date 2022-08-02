package com.ajebeku.composeapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajebeku.composeapp.ui.theme.ComposeAppTheme

@Composable
fun MainUiApp() {
    Surface(color = MaterialTheme.colors.background) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
        fillMax) {
            listOf("Ayoola, Ajebeku").forEach {
                ListItem(it)
            }
        }
    }
}


@Composable
fun ListItem(name: String) {
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.padding(4.dp)) {
        Text(text = "Hello, $name!")
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        MainUiApp()
    }
}