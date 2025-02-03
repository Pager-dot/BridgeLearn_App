package pratheekv39.bridgelearn.io.ui.simulations

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pratheekv39.bridgelearn.io.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcidBaseInteractiveLab(navController: NavController) {
    var selectedPH by remember { mutableFloatStateOf(7f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Acids and Bases") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) { // Navigates back
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
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Acids and Bases", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Select pH Level: ${selectedPH.toInt()}")
            Slider(
                value = selectedPH,
                onValueChange = { selectedPH = it },
                valueRange = 0f..14f,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            IonAnimationWithLegend(pH = selectedPH)
            Spacer(modifier = Modifier.height(16.dp))

            CommonExamplesDisplay()
        }
    }
}

@Composable
fun CommonExamplesDisplay() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "Common Daily Examples",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.Start)
        )
        val examples = listOf(
            Triple("Lemon", 2f, R.drawable.lime),
            Triple("Milk", 6.5f, R.drawable.milk),
            Triple("Water", 7f, R.drawable.water),
            Triple("Salt", 7f, R.drawable.salt),
            Triple("Toothpaste", 9.5f, R.drawable.toothpaste),
            Triple("Soap", 9.8f, R.drawable.soap),
            Triple("Cooking Oil", 7f, R.drawable.oil)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(examples) { (item, pH, iconRes) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "$item icon",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(item, style = MaterialTheme.typography.bodyMedium)
                    }
                    Text(
                        "pH value: $pH",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun IonAnimationWithLegend(pH: Float) {
    val hIonCount = remember { Animatable(0f) }
    val ohIonCount = remember { Animatable(0f) }

    LaunchedEffect(pH) {
        hIonCount.animateTo(if (pH < 7) (7 - pH) * 10 else 0f)
        ohIonCount.animateTo(if (pH > 7) (pH - 7) * 10 else 0f)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Draw H+ ions
            for (i in 0 until hIonCount.value.toInt()) {
                drawCircle(
                    color = Color.Red,
                    radius = 10f,
                    center = Offset(
                        x = (size.width * Math.random()).toFloat(),
                        y = (size.height * Math.random()).toFloat()
                    )
                )
            }

            // Draw OH- ions
            for (i in 0 until ohIonCount.value.toInt()) {
                drawCircle(
                    color = Color.Blue,
                    radius = 10f,
                    center = Offset(
                        x = (size.width * Math.random()).toFloat(),
                        y = (size.height * Math.random()).toFloat()
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LegendItem(color = Color.Red, label = "H+ Ions (Acidic)")
            LegendItem(color = Color.Blue, label = "OH- Ions (Basic)")
        }
    }
}

@Composable
fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(16.dp)) {
            drawCircle(color = color)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAcidBaseInteractiveLab() {
    val navController = rememberNavController()
    AcidBaseInteractiveLab(navController = navController)
}
