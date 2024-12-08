package com.appointment.myapplication.ui.presentation.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appointment.myapplication.Screens
import com.appointment.myapplication.key.R
import kotlinx.coroutines.delay
import okhttp3.internal.wait

@Composable
fun SplashScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(Screens.SIGNUP) {
            popUpTo(Screens.SPLASH) { inclusive = true }
        }

    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.tanialogo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 300.dp)
        )
    }
}