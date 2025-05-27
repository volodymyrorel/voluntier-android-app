package uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page

data class OrgPageStateUi(
    val uid: String,
    val email: String = "",
    val publicName: String = "",
    val govName: String = "",
    val type: String = "",
    val code: String = "",
    val description: String? = null,
    val isVerified: Boolean = false,
    val createdAt: String = "",
    val updatedAt: String = "",
    val demands: List<DemandUi> = emptyList(),
    val isUser: Boolean = false
) {

    data class DemandUi(
        val id: String,
        val type: String,
        val title: String,
        val description: String?,
        val targetSum: Double?,
        val currentSum: Double?
    )
}
