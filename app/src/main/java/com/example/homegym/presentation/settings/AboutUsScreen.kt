package com.example.homegym.presentation.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homegym.R
import com.example.homegym.presentation.screen_items.TextCaption
import com.example.homegym.presentation.screen_items.TextDescription
import com.example.homegym.presentation.screen_items.TextName
import com.example.homegym.ui.theme.Orange2

@Composable
fun AboutUsScreen(
    navController: NavController
){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextName(text = "About Us")
            Spacer(modifier = Modifier.height(32.dp))
            TextDescription(text = "This is my personal project that I built for purpose of testing my ability to develop and improve my skill in Android Development.")
        }
        
        TextCaption(text = "Developed by Soeun Kimsong", modifier = Modifier.align(Alignment.BottomStart).padding(16.dp))

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back", tint = Orange2
            )
        }
    }
}