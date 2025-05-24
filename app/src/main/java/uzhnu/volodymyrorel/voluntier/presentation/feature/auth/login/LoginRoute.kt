package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uzhnu.volodymyrorel.voluntier.presentation.core.theme.AppTheme

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginScreen(
        state,
        onLoginClicked = viewModel::onLoginClicked,
        onSignUpClicked = viewModel::onSignUpClicked,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged
    )
}

@Composable
fun LoginScreen(
    stateUi: LoginStateUi,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "VolunTier"
        )
        Text(
            text = "Log in"
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = stateUi.email,
            onValueChange = { onEmailChanged(it) },
            label = {
                Text("Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = stateUi.password,
            onValueChange = { onPasswordChanged(it) },
            label = {
                Text("Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Button(
                modifier = Modifier.weight(2.5F),
                onClick = onLoginClicked
            ) {
                Text("Log In")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .weight(3.5F)
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.labelSmall
                )
//                Button(
//                    onClick = onSignUpClicked,
//                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
//                    contentPadding = PaddingValues(0.dp)
//                ) {
//                    Text("Sign up")
//                }
                Text(
                    modifier = Modifier
                        .clickable(
                            enabled = true,
                            onClick = onSignUpClicked
                        ),
                    text = "Sign Up"
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            stateUi = LoginStateUi(
                email = "",
                password = ""
            ),
            onLoginClicked = {},
            onSignUpClicked = {},
            onEmailChanged = {},
            onPasswordChanged = {}
        )
    }
}