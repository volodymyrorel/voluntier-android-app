package uzhnu.volodymyrorel.voluntier.presentation.feature.org_page

import kotlinx.serialization.Serializable

object OrgPageConstants {

    @Serializable
    data class Args(
        val uid: String
    )
}