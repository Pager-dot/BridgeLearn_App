package pratheekv39.bridgelearn.io.theme
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel : ViewModel() {
    private val _darkMode = MutableStateFlow(false) // Default value is light mode
    val darkMode = _darkMode.asStateFlow()

    fun setDarkMode(isDark: Boolean) {
        _darkMode.value = isDark
    }
}