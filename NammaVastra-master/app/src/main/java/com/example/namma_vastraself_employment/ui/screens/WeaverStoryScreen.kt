package com.example.namma_vastraself_employment.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.namma_vastraself_employment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaverStoryScreen(navController: NavController) {
    val deepBrown = Color(0xFF2B1D0E)
    val warmIvory = Color(0xFFFFF7EF)
    val gold = Color(0xFFC89B3C)
    val clay = Color(0xFFE6B17A)
    val spice = Color(0xFFD25D4F)
    val forest = Color(0xFF2F6B5C)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Our Heritage", 
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold 
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = warmIvory,
                    titleContentColor = deepBrown,
                    navigationIconContentColor = deepBrown
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
                            warmIvory,
                            Color(0xFFFFE9DA),
                            Color(0xFFF6F2FF)
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
                // Main Story Header Image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.about1),
                        contentDescription = "Ilkal Weaving",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.82f)),
                                    startY = 400f
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(24.dp)
                    ) {
                        Surface(
                            color = gold,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "TRADITION",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = deepBrown
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "The Threads of Time",
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )
                        Text(
                            text = "Stories woven by hand, carried through generations",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }

                Column(modifier = Modifier.padding(24.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        HeritageChip(text = "Ilkal")
                        HeritageChip(text = "Molakalmuru")
                        HeritageChip(text = "Jamdani")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoStories,
                            contentDescription = null,
                            tint = forest,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "A Legacy of Pride",
                            style = MaterialTheme.typography.headlineSmall,
                            color = deepBrown,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Ilkal sarees are a symbol of pride for Karnataka. Characterized by the 'Topi Teni' seragu (pallu) and the unique joint of the body and pallu, these sarees represent centuries of craftsmanship. Each motif marks a ritual, a season, and a story that stays in the family.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF3D354F),
                        lineHeight = 28.sp
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        HeritageStatCard(title = "120+", subtitle = "Active Weavers", accent = clay)
                        HeritageStatCard(title = "40+", subtitle = "Patterns", accent = forest)
                        HeritageStatCard(title = "6", subtitle = "Regions", accent = spice)
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // Secondary Section
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(0.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.about2),
                                contentDescription = "Molakalmuru Silk",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "Molakalmuru's Silk Splendor",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = deepBrown
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Awarded the GI tag, Molakalmuru silk sarees are famous for their intricate borders and nature-inspired motifs. Every thread tells a story of the weaver's dedication, from winding the yarn to finishing the pallu.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF5B516E),
                                    lineHeight = 24.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Card(
                        shape = RoundedCornerShape(22.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = "From Loom to Market",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = deepBrown
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            HeritageStep(index = "01", text = "Yarn dyed with plant-based pigments")
                            HeritageStep(index = "02", text = "Hand-wefted borders and motifs")
                            HeritageStep(index = "03", text = "Quality checks and artisan signature")
                            HeritageStep(index = "04", text = "Fair pricing and direct customer reach")
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))
                    
                    Card(
                        shape = RoundedCornerShape(22.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.EmojiEvents,
                                    contentDescription = null,
                                    tint = spice
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Heritage Highlights",
                                    fontWeight = FontWeight.Bold,
                                    color = deepBrown
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            HeritageHighlight(icon = Icons.Default.Favorite, text = "Preserves ancestral weaving knowledge")
                            HeritageHighlight(icon = Icons.Default.Groups, text = "Supports cooperative-led artisan groups")
                            HeritageHighlight(icon = Icons.Default.Public, text = "Celebrates regional identity and craft")
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Mission Statement
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        forest,
                                        spice
                                    )
                                )
                            )
                            .padding(24.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "OUR MISSION",
                                style = MaterialTheme.typography.labelLarge,
                                color = gold,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Empowering local weavers by providing a direct platform to showcase their art to the world, ensuring fair wages and preserving Indian heritage.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                lineHeight = 26.sp
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

@Composable
private fun HeritageChip(text: String) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 0.dp,
        shadowElevation = 2.dp
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF3D354F)
        )
    }
}

@Composable
private fun RowScope.HeritageStatCard(title: String, subtitle: String, accent: Color) {
    Card(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = accent
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = Color(0xFF6C617A)
            )
        }
    }
}

@Composable
private fun HeritageStep(index: String, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFF1E7FF)
        ) {
            Text(
                text = index,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4B3C78)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 13.sp,
            color = Color(0xFF4C425E)
        )
    }
}

@Composable
private fun HeritageHighlight(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = Color(0xFFFFEFE3),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFFD77B2E),
                modifier = Modifier.padding(8.dp).size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontSize = 13.sp,
            color = Color(0xFF4C425E)
        )
    }
}
