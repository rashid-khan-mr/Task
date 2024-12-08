package com.appointment.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appointment.myapplication.data.model.DrugInfo
import com.appointment.myapplication.key.R
import com.appointment.myapplication.viewmodel.DashboadViewModel
import kotlinx.coroutines.delay
import okhttp3.internal.wait


@Composable
fun DetailsScreen( drugInfo: DrugInfo?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            drugInfo?.let {
                Text(text = "Name: ${it.name}", fontWeight = FontWeight.Bold)
                Text(text = "Dose: ${it.dose}")
                Text(text = "Strength: ${it.strength}")
            } ?: run {
                Text(text = "No drug information available.")
            }
        }
    }
}