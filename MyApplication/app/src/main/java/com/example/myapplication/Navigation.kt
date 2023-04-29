package com.example.myapplication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.BottomAppBar
import com.google.android.material.bottomappbar.BottomAppBar

sealed class AppScreen(val name: String) {
    object Home : AppScreen("Home")
    object Registration : AppScreen("Add Screen")
    object History : AppScreen("Details Screen")
    object Settings : AppScreen("Settings Screen")

    object Session : AppScreen("Session Screen")
}


@Composable
fun bottonBar(
    onSettingsButtonClicker: () -> Unit,
    onHomeButtonClicker: () -> Unit,
    onHistoryButtonClicker: () -> Unit,
    onRegistrationButtonClicker: () -> Unit
){
    val navController: NavHostController = rememberNavController()
    BottomAppBar {
        IconButton(onClick = onHomeButtonClicker) {
            Icon(Icons.Filled.Home, contentDescription = "Home")
        }
        IconButton(onClick = onSettingsButtonClicker) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings")
        }
        IconButton(onClick = onHistoryButtonClicker) {
            Icon(Icons.Filled.List, contentDescription = "History")
        }
        IconButton(onClick = onRegistrationButtonClicker) {
            Icon(Icons.Filled.Build, contentDescription = "Registration")
        }
    }
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
        },
        bottomBar = {
            bottonBar(
                onSettingsButtonClicker = {navController.navigate(AppScreen.Settings.name)},
                onHomeButtonClicker = {navController.navigate(AppScreen.Home.name)},
                onHistoryButtonClicker = {navController.navigate(AppScreen.History.name)},
                onRegistrationButtonClicker = {navController.navigate(AppScreen.Registration.name)}
            )
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
            HomeScreen()
        }
        composable(route = AppScreen.Settings.name){
            SettingsScreen()
        }
        composable(route = AppScreen.History.name){
            HistoryScreen()
        }
        composable(route = AppScreen.Session.name){
            SessionScreen()
        }
        composable(route = AppScreen.Registration.name){
            ShowSignUpPage()
        }
    }
}

