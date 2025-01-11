package pratheekv39.bridgelearn.io

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Color definitions
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Custom colors for EduBridge
val LightBlue = Color(0xFF6B9AC4)
val DarkBlue = Color(0xFF2E4057)
val AccentOrange = Color(0xFFF78154)
val BackgroundLight = Color(0xFFF8F9FA)
val BackgroundDark = Color(0xFF1A1B1E)

val updatedDarkColorScheme = darkColorScheme(
    primary = Color(0XFF37B6E9),       // Lavender Purple
    secondary = Color(0xFF03DAC6),     // Aqua Green
    tertiary = Color(0xFFFFB74D),      // Warm Amber
    background = Color(0xFF222834),    // Soft Black
    surface = Color(0xFF353F54),       // Darker Gray
    onPrimary = Color.Black,           // Text/icons on primary buttons
    onSecondary = Color.Black,         // Text/icons on secondary buttons
    onTertiary = Color(0XFF37B6E9),          // Text/icons on tertiary elements
    onBackground = Color(0xFFE0E0E0),  // Light Gray for text
    onSurface = Color(0xFFFFFFFF),     // White for high-emphasis content
    error = Color(0xFFCF6679),         // Muted Red
    surfaceVariant = Color(0xFF2C2C2C),

    primaryContainer=Color(0XFF37B6E9),
    onPrimaryContainer=Color.Black
)

val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = BackgroundLight,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun HomeTheme(darkTheme:Boolean,
              content: @Composable () -> Unit){
    val colorscheme=if(darkTheme){
        updatedDarkColorScheme
    }
    else{
        LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorscheme,
        content=content

    )

}
