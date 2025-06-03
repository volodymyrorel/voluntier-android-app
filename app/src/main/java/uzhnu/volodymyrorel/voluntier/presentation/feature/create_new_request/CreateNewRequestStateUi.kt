package uzhnu.volodymyrorel.voluntier.presentation.feature.create_new_request

import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand

data class CreateNewRequestStateUi(
    val type: String?,
    val title: String,
    val description: String,
    val sum: String
) {
    companion object {
        val DEFAULT = CreateNewRequestStateUi(
            type = null,
            title = "",
            description = "",
            sum = ""
        )
    }
}