package uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile

data class ProfileStateUi(
    val email: String
) {
    companion object {
        val DEFAULT = ProfileStateUi(
            email = "example1@test.com"
        )
    }
}