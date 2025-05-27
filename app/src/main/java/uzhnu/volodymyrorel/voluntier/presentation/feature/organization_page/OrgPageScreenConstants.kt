package uzhnu.volodymyrorel.voluntier.presentation.feature.organization_page

import kotlinx.serialization.Serializable

object OrgPageScreenConstants {

    @Serializable
    data class Args(
        val uid: String
    )
}