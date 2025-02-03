package pratheekv39.bridgelearn.io.ui.readings

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pratheekv39.bridgelearn.io.theme.AccentOrange
import pratheekv39.bridgelearn.io.theme.AfacadFontFamily
import pratheekv39.bridgelearn.io.theme.BackgroundLight
import pratheekv39.bridgelearn.io.theme.LightBlue
import java.io.InputStreamReader

data class Element(
    val name: String,
    val symbol: String,
    val description: String,
    val element_year: String,
    val element_discovered_name: String,
    val category: ElementCategory,
    val element_appearance: String,
    val atomicNumber: Int,
    val atomicMass: String,
    val group: Int,
    val period: Int,
    val electronConfiguration: String,
    val electronegativity: String,
    val atomicRadius: String,
    val ionRadius: String,
    val vanDerWaalsRadius: String,
    val ionizationEnergy: String,
    val electronAffinity: String,
    val oxidationStates: String,
    val standardState: String,
    val meltingPoint: String,
    val boilingPoint: String,
    val density: String,
    val element_crystal_structure: String,
    val element_electrical_conductivity: String,
    val element_magnetic_type: String,
    val element_volume_magnetic_susceptibility: String,
    val uses: String,
    val interesting_facts: List<String>
)

enum class ElementCategory {
    ALKALI_METAL,
    ALKALINE_EARTH_METAL,
    TRANSITION_METAL,
    POST_TRANSITION_METAL,
    METALLOID,
    NONMETAL,
    HALOGEN,
    NOBLE_GAS,
    LANTHANIDE,
    ACTINIDE,
    UNKNOWN,
    TRANSURANIC
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodicTableScreen(navController: NavController, context: Context) {
    var selectedElement by remember { mutableStateOf<Element?>(null) }
    val elements = remember { getPeriodicTableElementsFromJson(context) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Periodic Table",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = AfacadFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            PeriodicTable(
                elements = elements,
                onElementClick = { selectedElement = it }
            )
        }
    }
    selectedElement?.let { element ->
        ElementDetailsDialog(
            element = element,
            onDismiss = { selectedElement = null }
        )
    }
}

fun getPeriodicTableElementsFromJson(context: Context): List<Element> {
    val inputStream = context.assets.open("elements.json")
    val reader = InputStreamReader(inputStream)

    val elementListType = object : TypeToken<List<Element>>() {}.type
    return Gson().fromJson(reader, elementListType)
}

@Composable
fun PeriodicTable(
    elements: List<Element>,
    onElementClick: (Element) -> Unit
) {
    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(verticalScrollState)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 70.dp)
                .horizontalScroll(horizontalScrollState)
        ) {
            (1..18).forEach { group ->
                Box(
                    modifier = Modifier.size(78.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        group.toString(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = AfacadFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }

        Row {
            Column(
                modifier = Modifier.width(70.dp)
            ) {
                (1..7).forEach { period ->
                    Box(
                        modifier = Modifier.size(78.5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            period.toString(),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = AfacadFontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                }
            }

            // Main table
            Column(
                modifier = Modifier.horizontalScroll(horizontalScrollState)
            ) {
                for (period in 1..7) {
                    Row {
                        for (group in 1..18) {
                            val element = elements.find {
                                it.period == period && it.group == group &&
                                        it.category != ElementCategory.LANTHANIDE &&
                                        it.category != ElementCategory.ACTINIDE
                            }
                            if (element != null) {
                                ElementCard(element = element, onClick = onElementClick)
                            } else {
                                if (!((period == 6 && group == 3) || (period == 7 && group == 3))) {
                                    Spacer(modifier = Modifier.size(78.5.dp))
                                }
                            }

                            if (period == 6 && group == 3) {
                                LanthanideCard()
                            } else if (period == 7 && group == 3) {
                                ActinideCard()
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "* Lanthanides",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = AfacadFontFamily,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row {
                    elements
                        .filter { it.category == ElementCategory.LANTHANIDE }
                        .forEach { element ->
                            ElementCard(element = element, onClick = onElementClick)
                        }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "** Actinides",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = AfacadFontFamily,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row {
                    elements
                        .filter { it.category == ElementCategory.ACTINIDE }
                        .forEach { element ->
                            ElementCard(element = element, onClick = onElementClick)
                        }
                }
            }
        }
    }
}

@Composable
fun ElementCard(element: Element, onClick: (Element) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(70.dp)
            .clickable { onClick(element) },
        colors = CardDefaults.cardColors(
            containerColor = getCategoryColor(element.category)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 4.dp)
            ) {
                Text(
                    text = element.atomicNumber.toString(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = AfacadFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Text(
                text = element.symbol,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = AfacadFontFamily,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = element.name,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = AfacadFontFamily,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Composable
fun LanthanideCard() {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(70.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "57-71\n*Ln",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = AfacadFontFamily,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun ActinideCard() {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(70.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "89-103\n**An",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = AfacadFontFamily,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun ElementDetailsDialog(element: Element, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "${element.name} (${element.symbol})",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = AfacadFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Overview Section
                SectionWithBackground("Overview") {
                    ElementDetailItem("Description", element.description)
                    ElementDetailItem("Name", element.name)
                    ElementDetailItem("Year Discovered", element.element_year)
                    ElementDetailItem("Discovered By", element.element_discovered_name)
                    ElementDetailItem("Appearance", element.element_appearance)
                }

                // Properties Section
                SectionWithBackground("Properties") {
                    ElementDetailItem("Atomic Number", element.atomicNumber.toString())
                    ElementDetailItem("Atomic Mass", "${element.atomicMass} g/mol")
                    ElementDetailItem("Group", element.group.toString())
                    ElementDetailItem("Period", element.period.toString())
                    ElementDetailItem("Electron Configuration", element.electronConfiguration)
                    ElementDetailItem("Electronegativity", "${element.electronegativity} (Pauling scale)")
                    ElementDetailItem("Atomic Radius", "${element.atomicRadius} pm")
                    ElementDetailItem("Ion Radius", "${element.ionRadius} pm")
                    ElementDetailItem("Van Der Waals Radius", "${element.vanDerWaalsRadius} pm")
                    ElementDetailItem("Ionization Energy", "${element.ionizationEnergy} kJ/mol")
                    ElementDetailItem("Electron Affinity", "${element.electronAffinity} kJ/mol")
                    ElementDetailItem("Oxidation States", element.oxidationStates)
                    ElementDetailItem("Standard State", element.standardState)
                }

                // Temperatures Section
                SectionWithBackground("Temperatures") {
                    ElementDetailItem("Melting Point", "${element.meltingPoint} °C")
                    ElementDetailItem("Boiling Point", "${element.boilingPoint} °C")
                }

                // Other Properties Section
                SectionWithBackground("Other Properties") {
                    ElementDetailItem("Density", "${element.density} g/cm³")
                    ElementDetailItem("Crystal Structure", element.element_crystal_structure)
                    ElementDetailItem("Electrical Conductivity", element.element_electrical_conductivity)
                    ElementDetailItem("Magnetic Type", element.element_magnetic_type)
                    ElementDetailItem("Volume Magnetic Susceptibility", element.element_volume_magnetic_susceptibility)
                }

                // Uses Section
                SectionWithBackground("Uses") {
                    ElementDetailItem("Uses", element.uses)
                }

                // Interesting Facts Section
                SectionWithBackground("Interesting Facts") {
                    element.interesting_facts.forEach { fact ->
                        Text(
                            "- $fact",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = AfacadFontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    "Close",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = AfacadFontFamily,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    )
}

@Composable
private fun SectionWithBackground(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)
    ) {
        SectionHeaderWithBackground(title)
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(LightBlue)
                .padding(12.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun SectionHeaderWithBackground(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(LightBlue)
            .padding(12.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = AfacadFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Blue,
                fontSize = 20.sp
            ),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp), color = Color.DarkGray)
    }
}

@Composable
private fun ElementDetailItem(label: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = AfacadFontFamily,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = AfacadFontFamily,
                fontWeight = FontWeight.Normal
            )
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}

fun getCategoryColor(category: ElementCategory): Color {
    return when (category) {
        ElementCategory.ALKALI_METAL -> Color(0xFFFF8A80)
        ElementCategory.ALKALINE_EARTH_METAL -> Color(0xFFFFAB91)
        ElementCategory.TRANSITION_METAL -> LightBlue
        ElementCategory.POST_TRANSITION_METAL -> Color(0xFFBCAAA4)
        ElementCategory.METALLOID -> AccentOrange
        ElementCategory.NONMETAL -> Color(0xFFA5D6A7)
        ElementCategory.HALOGEN -> Color(0xFF90CAF9)
        ElementCategory.NOBLE_GAS -> Color(0xFFB39DDB)
        ElementCategory.LANTHANIDE -> Color(0xFFB3D9FF)
        ElementCategory.ACTINIDE -> Color(0xFFE1D1F2)
        ElementCategory.UNKNOWN -> BackgroundLight
        ElementCategory.TRANSURANIC -> Color(0xFF4CAF50)
    }
}