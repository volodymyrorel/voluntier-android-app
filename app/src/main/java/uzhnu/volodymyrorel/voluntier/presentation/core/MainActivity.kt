package uzhnu.volodymyrorel.voluntier.presentation.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uzhnu.volodymyrorel.voluntier.domain.navigation.Navigator
import uzhnu.volodymyrorel.voluntier.presentation.core.navigation.AppNavHost
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navHostController = rememberNavController()
                LaunchedEffect(navHostController) {
                    navigator.attach(navHostController, this@MainActivity)
                }
                AppNavHost(navHostController = navHostController)
            }
        }
    }
}