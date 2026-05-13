package com.example.namma_vastraself_employment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

// ===========================
// COLORS
// ===========================

private val PrimaryGold = Color(0xFFC89B3C)
private val DarkBrown = Color(0xFF2B1D0E)
private val Cream = Color(0xFFFDF8F2)
private val CardColor = Color(0xFFFFFFFF)
private val LightGold = Color(0xFFFFF1D6)

data class DashboardItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val dashboardItems = listOf(
        DashboardItem(
            "Trend Board",
            "Latest Market Styles",
            Icons.Default.TrendingUp,
            "trend_board"
        ),
        DashboardItem(
            "Loom Gallery",
            "Your Saree Collection",
            Icons.Default.Storefront,
            "loom_gallery"
        ),
        DashboardItem(
            "Upload Saree",
            "Add New Designs",
            Icons.Default.CloudUpload,
            "upload_saree"
        ),
        DashboardItem(
            "Price Calculator",
            "Calculate Fair Pricing",
            Icons.Default.Calculate,
            "price_calculator"
        ),
        DashboardItem(
            "Weaver Story",
            "Show Your Legacy",
            Icons.Default.AutoStories,
            "weaver_story"
        ),
        DashboardItem(
            "Orders",
            "Track Customer Orders",
            Icons.Default.ShoppingBag,
            "orders"
        )
    )

    Scaffold(
        containerColor = Cream,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DarkBrown
                ),
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "NAMMA VASTRA",
                            color = PrimaryGold,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 3.sp
                        )

                        Text(
                            text = "Empowering Local Weavers",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 11.sp
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(Cream)
        ) {

            // ===========================
            // HERO SECTION
            // ===========================

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

                AsyncImage(
                    model = "https://images.unsplash.com/photo-1610030469983-98e550d6193c?q=80&w=1200",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.75f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Preserving",
                        color = Color.White,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Indian Handloom Heritage",
                        color = PrimaryGold,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Directly connecting weavers with customers through technology.",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ===========================
            // QUICK STATS
            // ===========================

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                StatCard(
                    title = "120+",
                    subtitle = "Designs",
                    modifier = Modifier.weight(1f)
                )

                StatCard(
                    title = "40+",
                    subtitle = "Weavers",
                    modifier = Modifier.weight(1f)
                )

                StatCard(
                    title = "500+",
                    subtitle = "Customers",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // ===========================
            // DASHBOARD TITLE
            // ===========================

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {

                Text(
                    text = "Craftsman Dashboard",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )

                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(50))
                        .background(PrimaryGold)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ===========================
            // GRID
            // ===========================

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                userScrollEnabled = false
            ) {

                items(dashboardItems) { item ->

                    DashboardCard(
                        item = item,
                        onClick = {
                            navController.navigate(item.route)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

// ===========================
// DASHBOARD CARD
// ===========================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardCard(
    item: DashboardItem,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                PrimaryGold,
                                Color(0xFFE7C06D)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = DarkBrown,
                    modifier = Modifier.size(30.dp)
                )
            }

            Column {

                Text(
                    text = item.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.subtitle,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// ===========================
// STAT CARD
// ===========================

@Composable
fun StatCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = LightGold
        ),
        shape = RoundedCornerShape(22.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}