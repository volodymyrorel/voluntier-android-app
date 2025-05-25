package uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme

const val profileRoute = "profileRoute"

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        onLogOutClicked = viewModel::onLogOutClicked
    )
}

@Composable
fun ProfileScreen(
    onLogOutClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = onLogOutClicked
        ) {
            Text("LogOut")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            onLogOutClicked = {}
        )
    }
}