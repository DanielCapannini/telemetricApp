package com.example.appandroid

sealed class AppScreen(val name: String) {
    object Home : AppScreen("Home")
    object Registration : AppScreen("Add Screen")
    object Details : AppScreen("Details Screen")
    object Settings : AppScreen("Settings Screen")
}

