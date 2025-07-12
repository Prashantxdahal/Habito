package com.example.habito

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.habito.Navigation.MainScreen
import com.example.habito.ui.theme.HabitoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitoTheme {
                MainScreen()
            }
        }
    }
}
