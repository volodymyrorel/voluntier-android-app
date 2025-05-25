package uzhnu.volodymyrorel.voluntier.domain.demand

interface DemandRepository {
    suspend fun createNewDemand(
        type: String,
        title: String,
        description: String?,
        sum: Double?
    ) : Unit?
}