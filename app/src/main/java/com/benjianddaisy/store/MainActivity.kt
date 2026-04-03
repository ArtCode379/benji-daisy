package com.benjianddaisy.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.benjianddaisy.store.ui.composable.approot.AppRoot
import com.benjianddaisy.store.ui.theme.ProductAppBJDYSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppBJDYSTheme {
                AppRoot()
            }
        }
    }
}