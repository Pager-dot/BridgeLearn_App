
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.*


@Composable
fun PendulumSimulation( navController: NavController) {
    var length by remember { mutableStateOf(200f) }
    var initialAngle by remember { mutableStateOf(45f) }
    var isRunning by remember { mutableStateOf(false) }

    val g = 9.8f
    val lengthMeters = length / 100
    val period = 2 * PI * sqrt(lengthMeters / g)

    var time by remember { mutableStateOf(0f) }
    val angle = remember { Animatable(initialAngle) }

    LaunchedEffect(initialAngle) {
        angle.snapTo(initialAngle)
    }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (isRunning) {
                val omega = sqrt(g / lengthMeters)
                time += 0.016f
                val newAngle = initialAngle * cos(omega * time)
                angle.snapTo(newAngle)
                kotlinx.coroutines.delay(16)
            }
        } else {
            time = 0f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF37B6E9))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Pendulum Simulation", style = MaterialTheme.typography.titleLarge, color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp).background(Color(0xFFF8F9FA)),
            elevation =CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Length: ${length.toInt()/100}m ", color = Color.Black)
                Slider(
                    value = length,
                    onValueChange = { length = it },
                    valueRange = 100f..500f,
                    modifier = Modifier.padding(vertical = 8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Cyan,
                        activeTrackColor = Color.Blue
                    )
                )

                Text("Initial Angle: ${initialAngle.toInt()}°", color = Color.Black)
                Slider(
                    value = initialAngle,
                    onValueChange = { initialAngle = it },
                    valueRange = -90f..90f,
                    modifier = Modifier.padding(vertical = 8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Red,
                        activeTrackColor = Color.Magenta
                    )
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = { isRunning = true },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Green)
            ) {
                Text("Start", color = Color.White)
            }
            Button(
                onClick = { isRunning = false },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Red)
            ) {
                Text("Stop", color = Color.White)
            }
            Button(
                onClick = {
                    isRunning = false
                    time = 0f
                    GlobalScope.launch { angle.snapTo(initialAngle) }

                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Gray)
            ) {
                Text("Reset", color = Color.White)
            }
        }

        Text("Period: ${"%.2f".format(period)} s", color = Color.Yellow, modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )

        // Pendulum visualization
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val pivotX = size.width / 2
                val pivotY = size.height / 4
                val bobX = pivotX + length * sin(Math.toRadians(angle.value.toDouble())).toFloat()
                val bobY = pivotY + length * cos(Math.toRadians(angle.value.toDouble())).toFloat()


                drawCircle(
                    color = Color.Yellow,
                    radius = 12f,
                    center = Offset(pivotX, pivotY)
                )

                drawLine(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Cyan, Color.Blue)
                    ),
                    start = Offset(pivotX, pivotY),
                    end = Offset(bobX, bobY),
                    strokeWidth = 5f
                )

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color.Yellow, Color.DarkGray),
                        center = Offset(bobX, bobY),
                        radius = 30f
                    ),
                    radius = 30f,
                    center = Offset(bobX, bobY)
                )

                val velocity = -sqrt(g / lengthMeters) * length * sin(Math.toRadians(angle.value.toDouble())).toFloat()
                drawLine(
                    color = Color.White,
                    start = Offset(bobX, bobY),
                    end = Offset(bobX + velocity / 10, bobY),
                    strokeWidth = 5f
                )
            }
        }
    }
}

