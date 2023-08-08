package com.example.homegym.presentation.navigation

sealed class Screen(val route: String){
    object SplashScreen: Screen("splashscreen")
    object Home: Screen("home")
    object Discover: Screen("discover")
    object Progress: Screen("progress")
    object Settings: Screen("settings")
    object Workout: Screen("workout")
    object Exercise: Screen("exercise")
    object AboutUs: Screen("about_us")
}
