package com.ajebeku.composeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ajebeku.composeapp.ComposeApplication
import com.ajebeku.composeapp.ui.theme.ComposeAppTheme
import com.ajebeku.composeapp.ui.viewModel.EventViewModel
import com.ajebeku.composeapp.ui.viewModel.MyViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<EventViewModel> {
        MyViewModelFactory(application as ComposeApplication)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                MainUiApp(viewModel)
            }
        }
    }
}