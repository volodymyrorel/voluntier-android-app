package uzhnu.volodymyrorel.voluntier.domain.demand

import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand

interface DemandRepository {
    suspend fun createNewDemand(
        type: String,
        title: String,
        description: String?,
        sum: Double?
    ) : Unit?

    suspend fun getOrganizationDemands(orgId: String): List<Demand>

    suspend fun updateDemandCurrentSum(demandId: String, sum: Double): Unit?

    suspend fun getDemandCurrentSum(demandId: String): Double
}