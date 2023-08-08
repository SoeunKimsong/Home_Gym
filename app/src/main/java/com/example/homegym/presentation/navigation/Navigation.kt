package com.example.homegym.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.homegym.R


data class NavItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
)

val NavItems = listOf(
    NavItem("Home", R.drawable.ic_home, Screen.Home.route),
    NavItem("Discover", R.drawable.ic_dumbbell, Screen.Discover.route),
    NavItem("Progress", R.drawable.ic_progress, Screen.Progress.route),
    NavItem("Settings", R.drawable.ic_settings, Screen.Settings.route)
)
