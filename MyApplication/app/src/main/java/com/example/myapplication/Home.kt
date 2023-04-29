package com.example.myapplication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    Spacer(modifier = Modifier.size(100.dp))
    IconButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Filled.PlayArrow,
        contentDescription = "Session"
        )
    }
}