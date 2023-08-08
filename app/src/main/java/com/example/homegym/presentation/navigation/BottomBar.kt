package com.example.homegym.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    visible: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (NavItem) -> Unit
) {
    val items = remember { NavItems }
    val backStackEntry = navController.currentBackStackEntryAsState()

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = expandVertically(
            animationSpec = tween(easing = LinearEasing, delayMillis = 300),
            initialHeight = {-it}
        ),
        exit =  shrinkVertically(
            animationSpec = tween(easing = LinearEasing, delayMillis = 300),
            targetHeight = {-it}
        )
    ) {
        BottomNavigation(
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.background
        ) {
            items.forEach { item ->
                val selected = backStackEntry.value?.destination?.route == item.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        onItemClick(item)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Color.DarkGray,
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

    }

}