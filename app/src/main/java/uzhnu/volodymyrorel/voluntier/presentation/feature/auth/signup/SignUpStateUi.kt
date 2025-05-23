package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup

import androidx.compose.foundation.text.input.TextFieldState

data class SignUpStateUi(
    val email: TextFieldState,
    val password: TextFieldState,
    val repeatPassword: TextFieldState,
)