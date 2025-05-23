package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginScreen(
        state,
        onLoginClicked = viewModel::onLoginClicked,
        onSignUpClicked = viewModel::onSignUpClicked
    )
}

@Composable
fun LoginScreen(
    stateUi: LoginStateUi,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {

    }
}