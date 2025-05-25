package uzhnu.volodymyrorel.voluntier.domain.demand.entity

data class Demand(
    val ownerId: String,
    val type: String,
    val title: String,
    val description: String?,
    val sum: Double?,
    val createdAt: Long,
    val updatedAt: Long,
) {
    companion object {
        const val TYPE_FUNDRAISING = "fundraising"
        const val TYPE_MATERIAL = "material"
        const val TYPE_VOLUNTEERS = "volunteers"
    }
}
