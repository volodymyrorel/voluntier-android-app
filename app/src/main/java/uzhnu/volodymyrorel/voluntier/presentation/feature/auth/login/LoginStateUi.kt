package uzhnu.volodymyrorel.voluntier.presentation.feature.auth.login

data class LoginStateUi(
    val email: String,
    val password: String,
) {
    companion object {
        val DEFAULT = LoginStateUi(
            email = "",
            password = ""
        )
    }
}