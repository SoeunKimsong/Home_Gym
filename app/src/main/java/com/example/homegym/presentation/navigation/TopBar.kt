package com.example.homegym.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(
    navItem: NavItem?,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = expandVertically(
            animationSpec = tween(easing = LinearEasing, delayMillis = 300)
        ),
        exit = shrinkVertically(
            animationSpec = tween(easing = LinearEasing, delayMillis = 300)
        )
    ) {
        TopAppBar(
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.background
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary
                        )
                    ) {
                        append("Home ")
                    }
                    append("Gym")
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 32.dp)
            )
            if(navItem != null){
                Text(text = " - ", modifier = Modifier.padding(horizontal = 8.dp), fontSize = 30.sp)
                Icon(
                    painter = painterResource(id = navItem.icon),
                    contentDescription = navItem.title,
                    tint = MaterialTheme.colors.primary
                )
            }

        }
    }
}