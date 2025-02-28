package pratheekv39.bridgelearn.io.ui.simulations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.navigation.NavController
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun IonicBondingGame(navController: NavController) {
    var atom1X by remember { mutableStateOf(0f) }
    var atom2X by remember { mutableStateOf(400f) }
    var bondFormed by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("Use sliders to move atoms and form a bond!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F8E9)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            fontSize = 22.sp,
            color = Color.DarkGray
        )
        Box(modifier = Modifier.weight(1f)) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Draw bond if atoms are close enough
                if (bondFormed) {
                    drawLine(
                        color = Color.Black,
                        start = Offset(atom1X + 100, 400f),
                        end = Offset(atom2X + 100, 400f),
                        strokeWidth = 8f
                    )
                }
                drawCircle(color = Color.Blue, radius = 60f, center = Offset(atom1X + 100, 400f))
                drawCircle(color = Color.Red, radius = 60f, center = Offset(atom2X + 100, 400f))

                drawIntoCanvas { canvas ->
                    val paint = android.graphics.Paint().apply {
                        textSize = 50f
                        color = android.graphics.Color.WHITE
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                    canvas.nativeCanvas.drawText("H", atom1X + 100, 400f, paint)
                    canvas.nativeCanvas.drawText("Cl", atom2X + 100, 400f, paint)
                }
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Move Hydrogen (H)")
            Slider(value = atom1X, onValueChange = {
                atom1X = it
                bondFormed = checkBond(atom1X, atom2X)
                message = updateMessage(bondFormed)
            }, valueRange = 0f..800f)
            Text("Move Chlorine (Cl)")
            Slider(value = atom2X, onValueChange = {
                atom2X = it
                bondFormed = checkBond(atom1X, atom2X)
                message = updateMessage(bondFormed)
            }, valueRange = 0f..800f)
        }
        Button(
            onClick = {
                atom1X = 0f
                atom2X = 400f
                bondFormed = false
                message = "Use sliders to move atoms and form a bond!"
            },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66BB6A))
        ) {
            Text("Reset", color = Color.White)
        }
    }
}

fun checkBond(atom1X: Float, atom2X: Float): Boolean {
    return sqrt((atom1X - atom2X).pow(2)) < 200f
}

fun updateMessage(bond: Boolean): String {
    return if (bond) "Bond Formed! HCl Created!" else "Keep moving the atoms!"
}
