package com.example.viikkotehtava1.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.viikkotehtava1.ui.theme.Viikkotehtava1Theme
import com.example.viikkotehtava1.userinterface.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikkotehtava1Theme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.Companion.padding(innerPadding))
                }
            }
        }
    }
}