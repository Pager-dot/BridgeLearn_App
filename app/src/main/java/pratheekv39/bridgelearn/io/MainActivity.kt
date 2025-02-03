package pratheekv39.bridgelearn.io

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import pratheekv39.bridgelearn.io.navigation.NavGraph
import pratheekv39.bridgelearn.io.theme.HomeTheme
import pratheekv39.bridgelearn.io.theme.ThemeViewModel

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val darkMode by themeViewModel.darkMode.collectAsState()
          HomeTheme(darkTheme =darkMode)  {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   NavGraph()
                }
            }
        }
    }
}
