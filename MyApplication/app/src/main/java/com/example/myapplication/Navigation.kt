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
    object History : AppScreen("History Screen")
    object Settings : AppScreen("Settings Screen")
    object Session : AppScreen("Session Screen")
    object Profile : AppScreen("Profile Screen")
}


@Composable
fun bottonBar(
    onSettingsButtonClicker: () -> Unit,
    onProfileButtonClicker: () -> Unit,
    onHistoryButtonClicker: () -> Unit
){
    BottomAppBar {
        IconButton(onClick = onProfileButtonClicker) {
            Icon(Icons.Filled.Person, contentDescription = "Profile")
        }
        IconButton(onClick = onSettingsButtonClicker) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings")
        }
        IconButton(onClick = onHistoryButtonClicker) {
            Icon(Icons.Filled.List, contentDescription = "History")
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
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: AppScreen.Home.name

    if(currentScreen == AppScreen.Home.name){
        Scaffold(
            topBar = {
                TopAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = false,
                    navigateUp = { navController.navigateUp() },
                    onSettingsButtonClicker = {navController.navigate(AppScreen.Settings.name)})
            },
            bottomBar = {
                bottonBar(
                    onSettingsButtonClicker = {navController.navigate(AppScreen.Settings.name)},
                    onProfileButtonClicker = {navController.navigate(AppScreen.Profile.name)},
                    onHistoryButtonClicker = {navController.navigate(AppScreen.History.name)}
                )
            }
        ){
                innerPadding -> NavigationGraph(navController, innerPadding)
        }
    }else{
        Scaffold(
            topBar = {
                TopAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = true,
                    navigateUp = { navController.navigateUp() },
                    onSettingsButtonClicker = {navController.navigate(AppScreen.Settings.name)})
            }
        ){
                innerPadding -> NavigationGraph(navController, innerPadding)
        }
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
            HomeScreen(
                sessioButton = {navController.navigate(AppScreen.Session.name)}
            )
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
        composable(route = AppScreen.Profile.name){
            ShowSignUpPage()
        }
    }
}

