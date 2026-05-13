package com.example.namma_vastraself_employment.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.namma_vastraself_employment.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App logo",
                    modifier = Modifier
                        .size(120.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "NAMMA VASTRA", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(1400)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }
}
