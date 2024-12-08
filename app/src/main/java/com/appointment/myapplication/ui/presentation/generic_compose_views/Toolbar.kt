package com.appointment.myapplication

import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.appointment.myapplication.key.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(title: Int) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(title),
                style = TextStyle(
                    color = colorResource(id = R.color.dark_blue),
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp
                )
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.white)
        )
    )
}