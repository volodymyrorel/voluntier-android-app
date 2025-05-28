package uzhnu.volodymyrorel.voluntier.presentation.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel



@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel()
) {
    SplashScreen()
}

@Composable
fun SplashScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("VolunTier", fontSize = 40.sp)
    }
}