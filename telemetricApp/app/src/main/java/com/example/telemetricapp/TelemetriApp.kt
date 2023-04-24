package com.example.telemetricapp

sealed class AppScreen(val name: String) {
    object Home : AppScreen("Home")
    object Add : AppScreen("Add Screen")
    object Details : AppScreen("Details Screen")
    object Settings : AppScreen("Settings Screen")
}