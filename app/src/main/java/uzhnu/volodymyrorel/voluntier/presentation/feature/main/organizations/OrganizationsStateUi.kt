package uzhnu.volodymyrorel.voluntier.presentation.feature.main.organizations

import java.time.LocalDateTime

data class OrganizationsStateUi(
    val orgs: List<OrgStateUi>
) {

    data class OrgStateUi(
        val uid: String,
        val name: String,
        val description: String?,
        val createdAt: LocalDateTime
    )

    companion object {
        val DEFAULT = OrganizationsStateUi(
            orgs = emptyList()
        )
    }
}
