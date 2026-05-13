package com.example.namma_vastraself_employment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.namma_vastraself_employment.R
import androidx.compose.foundation.Image
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val logo = painterResource(id = R.drawable.logo)
                        Icon(painter = logo, contentDescription = "logo", tint = Color.Unspecified, modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "NAMMA VASTRA",
                                color = PrimaryGold,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.5.sp
                            )

                            Text(
                                text = "Empowering Local Weavers",
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 11.sp
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /* profile */ }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = PrimaryGold
                        )
                    }

                    IconButton(onClick = { /* notifications */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White.copy(alpha = 0.9f)
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
                .background(Cream)
                .verticalScroll(rememberScrollState())
        ) {

            // ===========================
            // HERO SECTION
            // ===========================

            // Search bar below the app bar
            Spacer(modifier = Modifier.height(12.dp))
            // Search bar
            val searchQuery = remember { mutableStateOf("") }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    placeholder = { Text(text = "Search designs, weavers, collections") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // HERO: layered image with color grading, rounded bottom and CTAs
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            ) {

                // Use local drawable resource for hero image. Place your image at:
                // app/src/main/res/drawable/hero_image.jpg
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Handloom hero",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // subtle warm color grading overlay (gold -> transparent)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0x44000000),
                                    Color(0x22000000)
                                )
                            )
                        )
                )

                // warm glow on left-bottom
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    PrimaryGold.copy(alpha = 0.18f),
                                    Color.Transparent
                                ),
                                center = androidx.compose.ui.geometry.Offset(220f, 420f),
                                radius = 600f
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(22.dp)
                ) {

                    Text(
                        text = "Preserving",
                        color = Cream,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Indian Handloom Heritage",
                        color = PrimaryGold,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Directly connect with customers, showcase your craft, and grow sustainably.",
                        color = Color(0xFFFAF7EE).copy(alpha = 0.95f),
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(
                            onClick = { navController.navigate("loom_gallery") },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGold),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.height(44.dp)
                        ) {
                            Text(text = "Explore Collections", color = DarkBrown, fontWeight = FontWeight.SemiBold)
                        }

                        OutlinedButton(
                            onClick = { navController.navigate("upload_saree") },
                            colors = ButtonDefaults.outlinedButtonColors(),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.height(44.dp)
                        ) {
                            Text(text = "Sell on NammaVastra", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // category chips
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TagChip(text = "Sarees")
                        TagChip(text = "Blouses")
                        TagChip(text = "Handloom")
                    }
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

                Column {
                    Text(
                        text = "Craftsman Dashboard",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(5.dp)
                            .clip(RoundedCornerShape(50))
                            .background(PrimaryGold)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))

            // filter dashboard items by search
            val filteredItems = remember(searchQuery.value) {
                if (searchQuery.value.isBlank()) dashboardItems
                else dashboardItems.filter {
                    it.title.contains(searchQuery.value, ignoreCase = true) ||
                        it.subtitle.contains(searchQuery.value, ignoreCase = true)
                }
            }

            if (filteredItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No results", color = Color.Gray)
                }
            } else {
                filteredItems.chunked(1).forEach { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowItems.forEach { item ->
                            DashboardCard(
                                item = item,
                                onClick = { navController.navigate(item.route) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            // ===========================
            // ABOUT US
            // ===========================

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    AsyncImage(
                        model = R.drawable.about,
                        contentDescription = "About us image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1.2f)) {
                    Text(text = "About Namma Vastra", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DarkBrown)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Namma Vastra connects local weavers to customers, preserves handloom traditions, and helps craftsmen grow sustainably.",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { navController.navigate("about") },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGold),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Learn more", color = DarkBrown)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun TagChip(text: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.12f),
        tonalElevation = 0.dp
    ) {
        Text(
            text = text,
            color = Color(0xFFFAF7EE),
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// ===========================
// DASHBOARD CARD
// ===========================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardCard(
    item: DashboardItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                // compact thumbnail for two-column layout
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF6E9DA)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        fontSize = 16.sp,
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

                // badge (example: show count for Orders, New for Upload)
                val badge = when (item.title) {
                    "Orders" -> "5"
                    "Upload Saree" -> "New"
                    else -> null
                }

                if (badge != null) {
                    Surface(
                        color = PrimaryGold,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(text = badge, modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp), color = DarkBrown)
                    }
                } else {
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
                }
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