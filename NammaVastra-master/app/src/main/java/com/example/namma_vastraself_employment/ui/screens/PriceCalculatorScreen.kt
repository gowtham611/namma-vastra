package com.example.namma_vastraself_employment.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.namma_vastraself_employment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceCalculatorScreen(navController: NavController) {
    var materialCost by remember { mutableStateOf("") }
    var laborCost by remember { mutableStateOf("") }
    var finalPrice by remember { mutableStateOf(0.0) }
    var marginPercent by remember { mutableStateOf(30f) }

    val midnight = Color(0xFF151225)
    val clay = Color(0xFFFFB17A)
    val rose = Color(0xFFFF6FB1)
    val mint = Color(0xFF6EE7C8)
    val softIvory = Color(0xFFFFF7F2)
    val slate = Color(0xFF3F3A55)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Price Calculator", 
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = softIvory,
                    titleContentColor = midnight,
                    navigationIconContentColor = midnight
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            softIvory,
                            Color(0xFFFFE6DD),
                            Color(0xFFF3F5FF)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Weaving process",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, softIvory),
                                    startY = 260f
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Price with confidence",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "Craft-first pricing for sustainable weaves",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 13.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .offset(y = (-26).dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(22.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = RoundedCornerShape(14.dp),
                                    color = clay.copy(alpha = 0.2f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Calculate,
                                        contentDescription = null,
                                        tint = clay,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Fair Trade Calculator",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = midnight
                                    )
                                    Text(
                                        text = "Estimate a price that respects effort",
                                        fontSize = 12.sp,
                                        color = slate
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                value = materialCost,
                                onValueChange = { materialCost = it },
                                label = { Text("Material Cost") },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                placeholder = { Text("Yarn, dyes, zari, trims") },
                                shape = RoundedCornerShape(16.dp),
                                prefix = { Text("₹ ") },
                                leadingIcon = {
                                    Icon(Icons.Default.Wallet, contentDescription = null, tint = clay)
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = clay,
                                    unfocusedBorderColor = Color(0xFFE4DAD4)
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedTextField(
                                value = laborCost,
                                onValueChange = { laborCost = it },
                                label = { Text("Labor & Skill") },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                placeholder = { Text("Days worked x daily rate") },
                                shape = RoundedCornerShape(16.dp),
                                prefix = { Text("₹ ") },
                                leadingIcon = {
                                    Icon(Icons.Default.TrendingUp, contentDescription = null, tint = rose)
                                },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = rose,
                                    unfocusedBorderColor = Color(0xFFE4DAD4)
                                )
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            Text(
                                text = "Profit margin: ${marginPercent.toInt()}%",
                                fontWeight = FontWeight.SemiBold,
                                color = midnight
                            )
                            Slider(
                                value = marginPercent,
                                onValueChange = { marginPercent = it },
                                valueRange = 10f..50f,
                                colors = SliderDefaults.colors(
                                    thumbColor = mint,
                                    activeTrackColor = mint,
                                    inactiveTrackColor = mint.copy(alpha = 0.2f)
                                )
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = {
                                    val m = materialCost.toDoubleOrNull() ?: 0.0
                                    val l = laborCost.toDoubleOrNull() ?: 0.0
                                    finalPrice = (m + l) * (1 + marginPercent / 100)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = midnight)
                            ) {
                                Text(
                                    "Calculate Selling Price",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    if (finalPrice > 0) {
                        Spacer(modifier = Modifier.height(20.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "Recommended Price",
                                    fontSize = 13.sp,
                                    color = slate
                                )
                                Text(
                                    text = "₹${"%,.0f".format(finalPrice)}",
                                    style = MaterialTheme.typography.displaySmall,
                                    color = midnight,
                                    fontWeight = FontWeight.ExtraBold
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(Color(0xFFF4F1FF))
                                        .padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text("Materials", fontSize = 11.sp, color = slate)
                                        Text(
                                            text = "₹${materialCost.toDoubleOrNull()?.let { "%,.0f".format(it) } ?: "0"}",
                                            fontWeight = FontWeight.SemiBold,
                                            color = midnight
                                        )
                                    }
                                    Column(horizontalAlignment = Alignment.End) {
                                        Text("Labor", fontSize = 11.sp, color = slate)
                                        Text(
                                            text = "₹${laborCost.toDoubleOrNull()?.let { "%,.0f".format(it) } ?: "0"}",
                                            fontWeight = FontWeight.SemiBold,
                                            color = midnight
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Surface(
                                    color = mint.copy(alpha = 0.25f),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(
                                        text = "${marginPercent.toInt()}% PROFIT MARGIN INCLUDED",
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = midnight,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}
