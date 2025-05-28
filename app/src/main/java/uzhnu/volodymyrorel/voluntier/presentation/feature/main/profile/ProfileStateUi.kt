package uzhnu.volodymyrorel.voluntier.presentation.feature.main.profile

data class ProfileStateUi(
    val email: String,
    val role: String,
    val surname: String,
    val name: String,
    val fatherName: String,
    val publicName: String,
    val govName: String,
    val type: String,
    val code: String
) {
    companion object {
        val DEFAULT = ProfileStateUi(
            email = "example1@test.com",
            role = "user",
            surname = "",
            name = "",
            fatherName = "",
            publicName = "",
            govName = "",
            type = "",
            code = ""
        )
    }
}