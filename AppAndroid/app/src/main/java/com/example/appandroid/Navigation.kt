package com.example.appandroid

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class AppScreen(val name: String) {
    object Home : AppScreen("Home")
    object Registration : AppScreen("Add Screen")
    object History : AppScreen("Details Screen")
    object Settings : AppScreen("Settings Screen")

    object Session : AppScreen("Session Screen")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    onSettingsButtonClicker: () -> Unit
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentScreen,
                fontWeight = FontWeight.Medium,
            )
        },
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp){
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }
        },
        actions = {
            if(currentScreen != AppScreen.Settings.name){
                IconButton(onClick = onSettingsButtonClicker){
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: AppScreen.Home.name

    Scaffold(
        topBar = {
            TopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onSettingsButtonClicker = {navController.navigate(AppScreen.Settings.name)})
        }
    ){
        innerPadding -> NavigationGraph(navController, innerPadding)
    }
}

@Composable
private  fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.name,
        modifier = modifier.padding(innerPadding)
    ){
        composable(route = AppScreen.Home.name){

        }
        composable(route = AppScreen.Settings.name){

        }
        composable(route = AppScreen.History.name){

        }
        composable(route = AppScreen.Session.name){

        }
        composable(route = AppScreen.Registration.name){
            
        }
    }
}

