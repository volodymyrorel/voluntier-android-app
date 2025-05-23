package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login

import androidx.compose.foundation.text.input.TextFieldState

data class LoginStateUi(
    val email: TextFieldState,
    val password: TextFieldState,
) {
    companion object {
        val DEFAULT = LoginStateUi(
            email = TextFieldState(""),
            password = TextFieldState("")
        )
    }
}