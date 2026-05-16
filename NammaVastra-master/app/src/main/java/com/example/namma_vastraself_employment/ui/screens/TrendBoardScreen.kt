package com.example.namma_vastraself_employment.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.namma_vastraself_employment.model.TrendItem
import com.example.namma_vastraself_employment.R
import com.example.namma_vastraself_employment.model.Saree
import com.example.namma_vastraself_employment.viewmodel.LoomViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendBoardScreen(navController: NavController, viewModel: LoomViewModel) {
    val heroPink = Color(0xFFFF5FA2)
    val heroOrange = Color(0xFFFF9F43)
    val heroTeal = Color(0xFF2CD8D5)
    val heroIndigo = Color(0xFF4B5CFF)
    val deepInk = Color(0xFF16122A)
    val warmWhite = Color(0xFFFFFBF7)
    val cardInk = Color(0xFF1E1A32)
    val accentLime = Color(0xFFB7FF4A)
    val accentRose = Color(0xFFFFD0E8)

    val glowTransition = rememberInfiniteTransition(label = "trendGlow")
    val glowShift by glowTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = androidx.compose.animation.core.LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowShift"
    )

    val sarees by viewModel.sarees.collectAsState()
    var selectedTrend by remember { mutableStateOf<TrendItem?>(null) }
    var showOrderDialog by remember { mutableStateOf(false) }
    var selectedQuantity by remember { mutableStateOf(1) }
    var selectedTrendForOrder by remember { mutableStateOf<TrendItem?>(null) }
    val context = LocalContext.current

    val trends = listOf(
        TrendItem(R.drawable.trend1, "Emerald Green Silk Ilkal"),
        TrendItem(R.drawable.trend2, "Magenta Banarasi Brocade"),
        TrendItem(R.drawable.trend3, "Classic Mustard Gold Zari"),
        TrendItem(R.drawable.trend4, "Peacock Blue Molakalmuru"),
        TrendItem(R.drawable.trend5, "Crimson Red Temple Border"),
        TrendItem(R.drawable.trend6, "Pastel Cotton Jamdani"),
        TrendItem(R.drawable.trend7, "Midnight Indigo Ajrakh"),
        TrendItem(R.drawable.trend8, "Sunrise Peach Chanderi"),
        TrendItem(R.drawable.trend9, "Terracotta Checks Cotton"),
        TrendItem(R.drawable.trend10, "Royal Blue Patola")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Trend Board", 
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
                    containerColor = warmWhite,
                    titleContentColor = deepInk,
                    navigationIconContentColor = deepInk
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
                            warmWhite,
                            Color(0xFFFFF1EC),
                            Color(0xFFF0F5FF)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(heroPink, heroOrange, heroTeal, heroIndigo),
                                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                                    end = androidx.compose.ui.geometry.Offset(1200f, 800f)
                                )
                            )
                            .clip(RoundedCornerShape(24.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(Color.White.copy(alpha = 0.35f), Color.Transparent),
                                        center = androidx.compose.ui.geometry.Offset(120f + 240f * glowShift, 60f + 120f * glowShift),
                                        radius = 520f
                                    )
                                )
                        )

                        Column(
                            modifier = Modifier
                                .padding(20.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = accentLime,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Season's Favorites 2024",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = accentLime,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.2.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Vibrant weaves, bold borders",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Serif,
                                color = Color.White
                            )

                            Text(
                                text = "Curated handloom drops with a fresh palette for festivals.",
                                fontSize = 13.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                FilterChip(
                                    selected = true,
                                    onClick = { },
                                    label = { Text("Trending", color = deepInk, fontWeight = FontWeight.Bold) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = accentRose,
                                        selectedLabelColor = deepInk
                                    )
                                )
                                FilterChip(
                                    selected = false,
                                    onClick = { },
                                    label = { Text("Silk", color = Color.White) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = Color.White.copy(alpha = 0.2f),
                                        labelColor = Color.White
                                    ),
                                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.5f))
                                )
                                FilterChip(
                                    selected = false,
                                    onClick = { },
                                    label = { Text("Cotton", color = Color.White) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        containerColor = Color.White.copy(alpha = 0.2f),
                                        labelColor = Color.White
                                    ),
                                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.5f))
                                )
                            }
                        }
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(trends) { item ->
                        TrendCard(
                            item,
                            cardInk = cardInk,
                            accentLime = accentLime,
                            onClick = { selectedTrend = item },
                            onOrderClick = {
                                selectedTrendForOrder = item
                                showOrderDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    if (showOrderDialog && selectedTrendForOrder != null) {
        OrderQuantityDialog(
            itemTitle = selectedTrendForOrder?.title.orEmpty(),
            quantity = selectedQuantity,
            onQuantityChange = { selectedQuantity = it },
            onConfirm = {
                val message = "Hi! I'm interested in ordering ${selectedQuantity} of your ${selectedTrendForOrder?.title} saree(s). Can you help me with availability and pricing? Email: www.gow2003@gmail.com"
                val url = "https://wa.me/6363330757?text=${Uri.encode(message)}"
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
                }
                showOrderDialog = false
                selectedQuantity = 1
            },
            onDismiss = { showOrderDialog = false }
        )
    }

    if (selectedTrend != null) {
        val relatedSarees = remember(selectedTrend, sarees) {
            pickRelatedSarees(selectedTrend?.title.orEmpty(), sarees)
        }

        AlertDialog(
            onDismissRequest = { selectedTrend = null },
            confirmButton = {
                TextButton(onClick = { selectedTrend = null }) {
                    Text("Close")
                }
            },
            title = {
                Text(
                    text = selectedTrend?.title.orEmpty(),
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = trendInsight(selectedTrend?.title.orEmpty()),
                        fontSize = 13.sp,
                        color = Color(0xFF3A3454)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Related sarees",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    relatedSarees.forEach { saree ->
                        RelatedSareeRow(saree)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        )
    }
}

@Composable
fun TrendCard(item: TrendItem, cardInk: Color, accentLime: Color, onClick: () -> Unit, onOrderClick: () -> Unit) {
    Card(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        colors = listOf(cardInk, Color(0xFF2C2345))
                    )
                )
                .clip(RoundedCornerShape(24.dp))
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(0.82f)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.55f)),
                                startY = 200f
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = accentLime,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Hot",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "New drop",
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 1
                )
                Text(
                    text = "Handwoven Collection",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.65f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onOrderClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = accentLime),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Order Now",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A)
                    )
                }
            }
        }
    }
}

@Composable
private fun RelatedSareeRow(saree: Saree) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF5F2FF))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = saree.imageUrl,
            contentDescription = saree.description,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = saree.description,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF262036),
                maxLines = 2
            )
            Text(
                text = "₹${"%,.0f".format(saree.price)}",
                fontSize = 12.sp,
                color = Color(0xFF6B5C8B)
            )
        }
    }
}

private fun pickRelatedSarees(trendTitle: String, sarees: List<Saree>): List<Saree> {
    if (sarees.isEmpty()) return emptyList()
    val keywords = trendTitle
        .lowercase()
        .split(" ")
        .map { it.trim() }
        .filter { it.length > 3 }

    val matched = sarees.filter { saree ->
        val text = saree.description.lowercase()
        keywords.any { text.contains(it) }
    }

    return if (matched.isNotEmpty()) matched.take(3) else sarees.take(3)
}

private fun trendInsight(title: String): String {
    return when {
        title.contains("Ilkal", ignoreCase = true) ->
            "Ilkal weaves are loved for their bold borders and soft drape. This season, emerald greens are paired with matte gold zari for a fresh festive look."
        title.contains("Banarasi", ignoreCase = true) ->
            "Banarasi brocades are back with saturated magentas and compact motifs. The shine is rich but balanced with minimal blouses for a modern contrast."
        title.contains("Kanchipuram", ignoreCase = true) || title.contains("Patola", ignoreCase = true) ->
            "Statement silks are trending with jewel tones and structured pleats. Pair with antique jewelry to keep the focus on the weave."
        title.contains("Cotton", ignoreCase = true) || title.contains("Jamdani", ignoreCase = true) ->
            "Breathable cottons are being styled with pastel palettes and airy drapes. Ideal for daytime celebrations and craft markets."
        else ->
            "Vibrant color stories and heritage borders are the highlight this season. These picks blend artisan craft with a modern, wearable finish."
    }
}

@Composable
private fun OrderQuantityDialog(
    itemTitle: String,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Order $itemTitle",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                Text(
                    text = "How many items would you like to order?",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(4.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("-", fontSize = 18.sp)
                    }

                    Text(
                        text = quantity.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .width(40.dp),
                        textAlign = TextAlign.Center
                    )

                    Button(
                        onClick = { if (quantity < 99) onQuantityChange(quantity + 1) },
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(4.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("+", fontSize = 18.sp)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB7FF4A))
            ) {
                Text("Proceed to WhatsApp", color = Color(0xFF1A1A1A), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
