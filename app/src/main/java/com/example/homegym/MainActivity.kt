package com.example.homegym

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homegym.presentation.discover.DiscoverScreen
import com.example.homegym.presentation.exercise.ExerciseScreen
import com.example.homegym.presentation.home.HomeScreen
import com.example.homegym.presentation.navigation.BottomBar
import com.example.homegym.presentation.navigation.NavItems
import com.example.homegym.presentation.navigation.NavRail
import com.example.homegym.presentation.navigation.Screen
import com.example.homegym.presentation.navigation.TopBar
import com.example.homegym.presentation.progress.ProgressScreen
import com.example.homegym.presentation.screen_items.WindowSizeClass
import com.example.homegym.presentation.screen_items.rememberWindowSizeClass
import com.example.homegym.presentation.settings.AboutUsScreen
import com.example.homegym.presentation.settings.SettingsScreen
import com.example.homegym.presentation.splashscreen.SplashScreen
import com.example.homegym.presentation.workout.WorkoutScreen
import com.example.homegym.ui.theme.HomeGymTheme
import com.example.homegym.util.checkNotificationPermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // force portrait for mobile
        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        // show the icon splash screen
        installSplashScreen()

        // request permission for notification
        if (!checkNotificationPermission(this)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(arrayOf(POST_NOTIFICATIONS), 1)
            }
        }

        setContent {

            var shouldShowTopBottomBar by rememberSaveable {
                mutableStateOf(false)
            }

            val navController = rememberNavController()
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination

            LaunchedEffect(key1 = currentDestination) {
                shouldShowTopBottomBar =
                    (currentDestination?.route?.substringBefore('?') != Screen.Workout.route) &&
                            (currentDestination?.route?.substringBefore('?') != Screen.Exercise.route) &&
                            (currentDestination?.route != Screen.AboutUs.route) &&
                            (currentDestination?.route != Screen.SplashScreen.route)

            }


            val windowScreen = rememberWindowSizeClass()

            HomeGymTheme {
                when (windowScreen.widthWindowSizeClass) {
                    WindowSizeClass.WindowType.COMPACT -> {
                        Scaffold(
                            bottomBar = {
                                BottomBar(
                                    visible = shouldShowTopBottomBar,
                                    navController = navController,
                                    onItemClick = { item ->
                                        navController.navigate(item.route) {
                                            popUpTo(Screen.Home.route) {
                                                saveState = true
                                            }
                                            restoreState = true
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            },
                            topBar = {
                                val currentItem = NavItems.find { item ->
                                    item.route == currentDestination?.route
                                }
                                TopBar(currentItem, shouldShowTopBottomBar)
                            }
                        ) {
                            MainScreen(
                                modifier = Modifier
                                    .animateContentSize()
                                    .fillMaxSize()
                                    .padding(it),
                                navController = navController
                            )

                        }
                    }

                    else -> {
                        Scaffold(
                            topBar = {
                                val currentItem = NavItems.find { item ->
                                    item.route == currentDestination?.route
                                }
                                TopBar(currentItem, shouldShowTopBottomBar)
                            }
                        ) {
                            Row {
                                NavRail(
                                    navController = navController,
                                    onItemClick = {item ->
                                        navController.navigate(item.route) {
                                            popUpTo(Screen.Home.route) {
                                                saveState = true
                                            }
                                            restoreState = true
                                            launchSingleTop = true
                                        }
                                    }
                                )

                                MainScreen(
                                    modifier = Modifier
                                        .animateContentSize()
                                        .fillMaxSize()
                                        .padding(it),
                                    navController = navController
                                )
                            }

                        }
                    }
                }

            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Discover.route) { DiscoverScreen(navController) }
        composable(Screen.Progress.route) { ProgressScreen() }
        composable(Screen.Settings.route) { SettingsScreen(navController) }
        composable(Screen.AboutUs.route) { AboutUsScreen(navController) }
        composable(Screen.SplashScreen.route) { SplashScreen(navController) }

        composable(
            Screen.Workout.route + "?workout_id={workout_id}",
            arguments = listOf(
                navArgument("workout_id") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) { entry ->
            WorkoutScreen(
                workoutId = entry.arguments?.getInt("workout_id") ?: 1,
                navController = navController
            )
        }

        composable(
            Screen.Exercise.route + "?workout_id={workout_id}",
            arguments = listOf(
                navArgument("workout_id") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) { entry ->
            ExerciseScreen(
                workoutId = entry.arguments?.getInt("workout_id") ?: 1,
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    HomeGymTheme {
        Scaffold(
            bottomBar = {
                BottomBar(
                    visible = true,
                    navController = navController,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            },
            topBar = { TopBar(NavItems[0], true) }
        ) {
            MainScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                navController = navController
            )
        }
    }
}