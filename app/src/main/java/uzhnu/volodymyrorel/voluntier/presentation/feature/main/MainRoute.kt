package uzhnu.volodymyrorel.voluntier.presentation.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.components.MainBottomBar
import uzhnu.volodymyrorel.voluntier.presentation.feature.main.components.MainNavHost

@Composable
fun MainRoute(
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    MainScreen(
        state = state
    )
}

@Composable
fun MainScreen(
    state: MainStateUi
) {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize().background(color = Color.LightGray),
        bottomBar = {
            MainBottomBar(navHostController = navController, role = state.role)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MainNavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = innerPadding.calculateBottomPadding()),
                navController = navController,
                role = state.role
            )
        }
    }
}