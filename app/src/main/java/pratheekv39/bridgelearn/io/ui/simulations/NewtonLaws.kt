package pratheekv39.bridgelearn.io.ui.simulations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun NewtonsLawsSimulation(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color(0xFF121212)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Newton's Laws of Motion", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item { SimpleFirstLaw() }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { SimpleSecondLaw() }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { SimpleThirdLaw() }
    }
}

@Composable
fun SimpleFirstLaw() {
    var isMoving by remember { mutableStateOf(false) }
    val position by animateFloatAsState(targetValue = if (isMoving) 200f else 0f, animationSpec = tween(1000))

    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("First Law: Object at Rest Stays at Rest", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Button(onClick = { isMoving = !isMoving }) { Text("Apply Force") }
            Canvas(modifier = Modifier.size(300.dp, 100.dp)) {
                drawCircle(Color.White, radius = 20f, center = Offset(position + 50f, 50f))
            }
        }
    }
}

@Composable
fun SimpleSecondLaw() {
    var force by remember { mutableStateOf(10f) }
    var mass by remember { mutableStateOf(5f) }
    val acceleration = force / mass
    val position by animateFloatAsState(targetValue = acceleration * 100, animationSpec = tween(1000))

    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Second Law: F = ma", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Slider(value = force, onValueChange = { force = it }, valueRange = 5f..50f,
                colors = SliderDefaults.colors(Color.White))
            Text("Force: $force", color = Color.White)
            Slider(value = mass, onValueChange = { mass = it }, valueRange = 1f..10f,
                colors = SliderDefaults.colors(Color.White))
            Text("Mass: $mass", color = Color.White)
            Canvas(modifier = Modifier.size(300.dp, 100.dp)) {
                drawCircle(Color.Red, radius = 20f, center = Offset(position + 50f, 50f))
            }
        }
    }
}

@Composable
fun SimpleThirdLaw() {
    var isReleased by remember { mutableStateOf(false) }
    val balloonPosition by animateFloatAsState(targetValue = if (isReleased) -150f else 0f, animationSpec = tween(1500))

    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Third Law: Action & Reaction", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Canvas(modifier = Modifier.size(100.dp, 150.dp)) {
                    drawOval(Color.Magenta, topLeft = Offset(30f, 100f + balloonPosition), size = Size(40f, 60f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { isReleased = !isReleased }) { Text("Release Balloon") }
        }
    }
}
