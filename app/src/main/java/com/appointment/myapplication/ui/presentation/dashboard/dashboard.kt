package com.appointment.myapplication.ui.presentation.dashboard
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appointment.myapplication.viewmodel.DashboadViewModel
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.appointment.myapplication.CustomToolbar
import com.appointment.myapplication.Screens
import com.appointment.myapplication.data.model.DrugInfo
import com.appointment.myapplication.key.R
import com.google.gson.Gson

@Composable
internal fun  DashboardScreen(navController: NavController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    BackHandler {
        // Close the app
        (context as? Activity)?.finish()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomToolbar(R.string.main)
        },
        content = { paddingValue ->

            DrugInfoList(navController)
        })
}


@Composable
fun DrugInfoList(
    navController: NavController,
    dashboadViewModel: DashboadViewModel = hiltViewModel()
) {
    val drugInfoList by dashboadViewModel.drugInfoList.collectAsState()
    LazyColumn {
        items(drugInfoList) { drugInfo ->
            DrugInfoItem(drugInfo, navController) // Composable to display each DrugInfo item
        }
    }
}

@Composable
fun DrugInfoItem(drugInfo: DrugInfo, navController: NavController
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                val drugInfoJson = Gson().toJson(drugInfo)
                navController.navigate("${Screens.DETAILSDRUG}/$drugInfoJson")
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = drugInfo.name, fontWeight = FontWeight.Bold)
            Text(text = drugInfo.dose)
            Text(text = drugInfo.strength)
        }
    }
}