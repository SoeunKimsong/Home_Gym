package com.example.homegym.presentation.screen_items

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
){
    Text(
        text = text,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = color
    )
}

@Composable
fun TextName(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black
){
    Text(
        text = text,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = color
    )
}

@Composable
fun TextCaption(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black
){
    Text(
        text = text,
        modifier = modifier,
        fontSize = 14.sp,
        color = color
    )
}

@Composable
fun TextDescription(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black
){
    Text(
        text = text,
        modifier = modifier,
        fontSize = 16.sp,
        color = color
    )
}


