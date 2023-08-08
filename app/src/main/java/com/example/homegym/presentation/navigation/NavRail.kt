package com.example.homegym.presentation.navigation

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun NavRail(
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (NavItem)->Unit
) {
    NavigationRail(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 5.dp
    ) {
        val items = remember {
            NavItems
        }
        val backStackEntry = navController.currentBackStackEntryAsState()

        items.forEach { item ->
            val selected = backStackEntry.value?.destination?.route == item.route
            NavigationRailItem(
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

@Preview
@Composable
fun PreviewNavRail() {
    NavRail(navController = rememberNavController(), onItemClick = {})
}