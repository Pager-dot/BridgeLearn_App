package pratheekv39.bridgelearn.io.ui.simulations

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun SpringSimulation(navController: NavController) {
    var springConstant by remember { mutableStateOf(50f) }
    var mass by remember { mutableStateOf(1f) }
    var displacement by remember { mutableStateOf(100f) }
    var isRunning by remember { mutableStateOf(false) }

    val equilibriumY = 400f
    val g = 9.8f
    val displacementMeters = displacement / 100
    val angularFrequency = sqrt(springConstant / mass)
    val period = (2 * PI / angularFrequency).toFloat()

    var time by remember { mutableStateOf(0f) }
    val currentDisplacement = remember { Animatable(displacement) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (isRunning) {
                time += 0.016f
                val newDisplacement = displacement * cos(angularFrequency * time)
                currentDisplacement.snapTo(newDisplacement)
                kotlinx.coroutines.delay(16) // 60 FPS
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
            Text("Spring Simulation", style = MaterialTheme.typography.titleLarge, color = Color.Black, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp).background(Color(0xFFF8F9FA)),
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(12.dp),

            ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Spring Constant: ${springConstant.toInt()} N/m", color = Color.Black)
                Slider(
                    value = springConstant,
                    onValueChange = { springConstant = it },
                    valueRange = 10f..100f,
                    modifier = Modifier.padding(vertical = 8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Cyan,
                        activeTrackColor = Color.Blue
                    )
                )

                Text("Mass: ${"%.2f".format(mass)} kg", color = Color.Black)
                Slider(
                    value = mass,
                    onValueChange = { mass = it },
                    valueRange = 0.5f..5f,
                    modifier = Modifier.padding(vertical = 8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Red,
                        activeTrackColor = Color.Magenta
                    )
                )

                Text("Initial Displacement: ${displacement.toInt()/100} m", color = Color.Black)
                Slider(
                    value = displacement,
                    onValueChange = { displacement = it },
                    valueRange = 50f..500f,
                    modifier = Modifier.padding(vertical = 8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Yellow,
                        activeTrackColor = Color.Green
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
                    GlobalScope.launch {
                        currentDisplacement.snapTo(displacement)
                    }

                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Gray)
            ) {
                Text("Reset", color = Color.White)
            }
        }

        Text("Period: ${"%.2f".format(period)} s", color = Color.Yellow, modifier = Modifier.padding(8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold)


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val springX = size.width / 2
                val springY = equilibriumY + currentDisplacement.value

                val coilCount = 10
                val springHeight = springY - equilibriumY
                val segmentHeight = springHeight / coilCount
                val amplitude = 20f

                for (i in 0 until coilCount) {
                    val startX = springX + if (i % 2 == 0) -amplitude else amplitude
                    val endX = springX + if (i % 2 == 0) amplitude else -amplitude
                    val startY = equilibriumY + i * segmentHeight
                    val endY = equilibriumY + (i + 1) * segmentHeight

                    drawLine(
                        color = Color.Black,
                        start = Offset(startX, startY),
                        end = Offset(endX, endY),
                        strokeWidth = 4f,
                        pathEffect = PathEffect.cornerPathEffect(4f)
                    )
                }
                drawRect(
                    color = Color.White,
                    topLeft = Offset(springX - 40, springY),
                    size = androidx.compose.ui.geometry.Size(80f, 40f)
                )
                val kineticEnergy = 0.5f * mass * (angularFrequency * currentDisplacement.value / 100).pow(2)
                val potentialEnergy = 0.5f * springConstant * (currentDisplacement.value / 100).pow(2)
                val totalEnergy = kineticEnergy + potentialEnergy

            }
        }
    }
}

