package com.appointment.myapplication


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appointment.myapplication.data.model.DrugInfo
import com.appointment.myapplication.ui.presentation.dashboard.DashboardScreen
import com.appointment.myapplication.ui.presentation.splash_screen.SplashScreen
import com.appointment.myapplication.ui.presentation.signup.SignupScreen
import com.google.gson.Gson

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SPLASH
    ) {
        composable(Screens.SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(Screens.DASHBOARD) {
            DashboardScreen(navController)
        }

        composable("${Screens.DETAILSDRUG}/{drugInfoJson}") { backStackEntry ->
            val drugInfoJson = backStackEntry.arguments?.getString("drugInfoJson")
            val drugInfo = drugInfoJson?.let { Gson().fromJson(it, DrugInfo::class.java) }
            DetailsScreen(drugInfo)
        }


        composable(Screens.SIGNUP) {
            SignupScreen(successfulLogin = {
                navController.navigate(Screens.DASHBOARD) {
                    popUpTo(Screens.SPLASH) { inclusive = true }
                }
            })
        }

    }
}
