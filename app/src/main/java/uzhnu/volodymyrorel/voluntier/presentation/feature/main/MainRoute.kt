package uzhnu.volodymyrorel.voluntier.presentation.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.components.MainBottomBar
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.components.MainNavHost

@Composable
fun MainRoute(
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    MainScreen()
}

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MainBottomBar(navHostController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MainNavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = innerPadding.calculateBottomPadding()),
                navController = navController
            )
        }
    }
}