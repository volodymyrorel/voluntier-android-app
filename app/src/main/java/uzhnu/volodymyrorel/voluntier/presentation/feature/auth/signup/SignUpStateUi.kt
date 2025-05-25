package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.signup

import androidx.compose.foundation.text.input.TextFieldState

data class SignUpStateUi(
    val email: String,
    val password: String,
    val repeatPassword: String,
    val isOrganization: Boolean,
    val userSurname: String,
    val userName: String,
    val userFatherName: String,
    val userIsAdult: Boolean,
    val orgPublicName: String,
    val orgGovName: String,
    val orgType: String,
    val orgCode: String
) {
    companion object {
        val DEFAULT = SignUpStateUi(
            email = "",
            password = "",
            repeatPassword = "",
            isOrganization = false,
            userSurname = "",
            userName = "",
            userFatherName = "",
            userIsAdult = false,
            orgPublicName = "",
            orgGovName = "",
            orgType = "",
            orgCode = ""
        )
    }
}