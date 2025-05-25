package uzhnu.volodymyrorel.voluntier.presentation.feature.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

const val homeRoute = "homeRoute"

@Composable
fun HomeRoute(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    HomeScreen(
        onCreateRequestClicked = viewModel::onCreateNewRequestClicked
    )
}

@Composable
fun HomeScreen(
    onCreateRequestClicked: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Text("HomeScreen")
        Button(
            onClick = onCreateRequestClicked
        ) {
            Text(text = "Create request")
        }
    }

}