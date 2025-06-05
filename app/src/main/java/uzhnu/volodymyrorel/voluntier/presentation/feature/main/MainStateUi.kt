package uzhnu.volodymyrorel.voluntier.presentation.feature.main

data class MainStateUi(
    val role: String
) {
    companion object {
        val DEFAULT = MainStateUi(
            role = ""
        )
    }
}
