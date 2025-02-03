package pratheekv39.bridgelearn.io.ui.readings

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pratheekv39.bridgelearn.io.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnergyConservationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Energy Conservation") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Heading
            Text(
                text = "Types of Energy",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE65100)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Energy Diagram
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height
                    val centerX = width / 2
                    val centerY = height / 2

                    val color1 = Color(0x4DFFD700)
                    val color2 = Color(0x4D00BFFF)
                    val color3 = Color(0x4DB2DFDB)
                    val color4 = Color(0x4DE6E6FA)

                    drawRect(
                        color = color1,
                        topLeft = Offset(0f, 0f),
                        size = Size(centerX, centerY)
                    )
                    drawRect(
                        color = color2,
                        topLeft = Offset(centerX, 0f),
                        size = Size(centerX, centerY)
                    )
                    drawRect(
                        color = color3,
                        topLeft = Offset(0f, centerY),
                        size = Size(centerX, centerY)
                    )
                    drawRect(
                        color = color4,
                        topLeft = Offset(centerX, centerY),
                        size = Size(centerX, centerY)
                    )

                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, 0f),
                        end = Offset(width, height),
                        strokeWidth = 2f
                    )
                    drawLine(
                        color = Color.Black,
                        start = Offset(width, 0f),
                        end = Offset(0f, height),
                        strokeWidth = 2f
                    )

                    drawLine(
                        color = Color.Black,
                        start = Offset(centerX, 0f),
                        end = Offset(centerX, height),
                        strokeWidth = 2f
                    )
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, centerY),
                        end = Offset(width, centerY),
                        strokeWidth = 2f
                    )
                }

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .border(1.dp, Color(0xFF4169E1), shape = CircleShape)
                        .background(Color(0xFF87CEEB), shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.earth),
                        contentDescription = "Earth",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                EnergyTypeSection(
                    modifier = Modifier
                        .padding(top = 0.1.dp, start = 65.dp),
                    title = "Electrical Energy",
                    imageRes = R.drawable.light,
                    imageSize = 100.dp,
                    textSize = 15.sp,
                    textOffsetY = (-19).dp,
                    textOffsetX = (37).dp
                )

                EnergyTypeSection(
                    modifier = Modifier
                        .padding(top = 62.dp, start = 0.1.dp),
                    title = "Mechanical Energy",
                    imageRes = R.drawable.mechanical,
                    imageSize = 90.dp,
                    textSize = 15.sp,
                    textOffsetY = (-15).dp,
                    textOffsetX = (37).dp
                )

                EnergyTypeSection(
                    modifier = Modifier
                        .padding(top = 220.dp, start = 0.1.dp),
                    title = "Thermal Energy",
                    imageRes = R.drawable.thermal,
                    imageSize = 100.dp,
                    textSize = 15.sp,
                    textOffsetY = (-120).dp,
                    textOffsetX = (40).dp
                )

                EnergyTypeSection(
                    modifier = Modifier
                        .padding(top = 230.dp, start = 0.1.dp)
                        .offset(x=55.dp,y=62.dp),
                    title = "Gravitational Energy",
                    imageRes = R.drawable.gravitational,
                    imageSize = 105.dp,
                    textSize = 15.sp,
                    textOffsetY = (-126).dp,
                    textOffsetX = (35).dp
                )

                EnergyTypeSection(
                    modifier = Modifier
                        .padding(top = 230.dp, start = 0.1.dp)
                        .offset(x=200.dp,y=40.dp),
                    title = "Wind Energy",
                    imageRes = R.drawable.wind,
                    imageSize = 105.dp,
                    textSize = 15.sp,
                    textOffsetY = (-130).dp,
                    textOffsetX = (-23).dp
                )

                EnergyTypeSection(
                    modifier = Modifier
                        .padding(top = 220.dp, start = 0.1.dp)
                        .offset(x=290.dp,y=0.1.dp),
                    title = "Chemical Energy",
                    imageRes = R.drawable.chemical,
                    imageSize = 105.dp,
                    textSize = 15.sp,
                    textOffsetY = (-130).dp,
                    textOffsetX = (-50).dp
                )

                EnergyTypeSection(
                    modifier = Modifier
                        .padding(top = 45.dp, start = 0.1.dp)
                        .offset(x=270.dp,y=0.1.dp),
                    title = "Nuclear Energy",
                    imageRes = R.drawable.nuclear,
                    imageSize = 115.dp,
                    textSize = 15.sp,
                    textOffsetY = (-20).dp,
                    textOffsetX = (-40).dp
                )

                EnergyTypeSection(
                    modifier = Modifier
                        .padding(top = 0.1.dp, start = 0.1.dp)
                        .offset(x=190.dp,y=0.1.dp),
                    title = "Solar Energy",
                    imageRes = R.drawable.solar,
                    imageSize = 115.dp,
                    textSize = 15.sp,
                    textOffsetY = (-20).dp,
                    textOffsetX = (-30).dp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            FlashCard()

            Spacer(modifier = Modifier.height(24.dp))
            EnergyConservationTips()
        }
    }
}

@Composable
fun EnergyTypeSection(
    modifier: Modifier = Modifier,
    title: String,
    imageRes: Int,
    imageSize: Dp = 100.dp,
    textSize: TextUnit = 14.sp,
    textOffsetY: Dp = 10.dp,
    textOffsetX: Dp = 10.dp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier
                .size(imageSize)
                .padding(8.dp)
        )

        Text(
            text = title.replace(" ", "\n"),
            style = TextStyle(
                fontSize = textSize,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .offset(x=textOffsetX, y = textOffsetY)
        )
    }
}

// Flash Card Composable
@Composable
fun FlashCard() {
    // Card Content
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(8.dp),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFFFFACD)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.note_icon),
                    contentDescription = "Note Icon",
                    tint = Color(0xFF8B4513),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Take Note!",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF8B4513)
                )
            }
            Spacer(modifier = Modifier.height(8.dp)) // Space between title and content
            // Main Content
            Text(
                text = "Energy can neither be created nor be destroyed. " +
                        "It may be transformed from one form to another.\n" +
                        "                          ~ Law of Conservation of Energy",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun EnergyConservationTips() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8F5E9)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "  5 Ways to Conserve Energy",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE65100),
            )
            Spacer(modifier = Modifier.height(16.dp))
            ConservationTipItem(
                title = "Use less electricity",
                description = "We can save energy by only utilizing air heaters or air conditioning systems when we are at home or at work.",
                icon = Icons.Default.Bolt,
                isIconOnRight = false
            )
            Spacer(modifier = Modifier.height(4.dp))
            ConservationTipItem(
                title = "Save water",
                description = "The source, processing, and delivery of water need a significant amount of energy. We may save energy by using less water around the house.",
                icon = Icons.Default.WaterDrop,
                isIconOnRight = true
            )
            Spacer(modifier = Modifier.height(4.dp))
            ConservationTipItem(
                title = "Walk more, Drive less",
                description = "Cars are a major energy guzzler on the world. Instead of driving, try walking to the store or to work; you'll get some exercise in the process.",
                icon = Icons.Default.DirectionsWalk,
                isIconOnRight = false
            )
            Spacer(modifier = Modifier.height(4.dp))
            ConservationTipItem(
                title = "Eat wisely",
                description = "Farming, harvesting, transporting, and cooking food need a tremendous amount of energy. Reduce the energy connected with food by refusing to waste any food; eat leftovers and only buy what you intend to eat.",
                icon = Icons.Default.Restaurant,
                isIconOnRight = true
            )
            Spacer(modifier = Modifier.height(4.dp))
            ConservationTipItem(
                title = "Get other people involved",
                description = "A single individual's efforts to conserve energy can have a tremendous influence. However, it is far better if we all work together to conserve energy.",
                icon = Icons.Default.Groups,
                isIconOnRight = false
            )
        }
    }
}

@Composable
fun ConservationTipItem(
    title: String,
    description: String,
    icon: ImageVector,
    isIconOnRight: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isIconOnRight) {
            // Icon on the left
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0xFF2E7D32), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Text content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
        } else {
            // Text content on the left
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Icon on the right
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0xFF2E7D32), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}