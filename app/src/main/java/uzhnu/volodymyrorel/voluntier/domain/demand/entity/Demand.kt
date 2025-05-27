package uzhnu.volodymyrorel.voluntier.domain.demand.entity

import java.time.LocalDateTime

data class Demand(
    val uid: String,
    val ownerId: String,
    val type: String,
    val title: String,
    val description: String?,
    val targetSum: Double?,
    val currentSum: Double?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        const val TYPE_FUNDRAISING = "fundraising"
        const val TYPE_MATERIAL = "material"
        const val TYPE_VOLUNTEERS = "volunteers"
    }
}
