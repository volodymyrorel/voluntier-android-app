package uzhnu.volodymyrorel.voluntier.domain.demand

import uzhnu.volodymyrorel.voluntier.domain.demand.entity.Demand

interface DemandRepository {
    suspend fun createNewFundraisingDemand(
        title: String,
        description: String?,
        sum: Double?
    ) : Unit?

    suspend fun createNewVolunteersDemand(
        title: String,
        description: String
    ) : Unit?

    suspend fun createNewMaterialDemand(
        title: String,
        description: String
    ) : Unit?

    suspend fun getOrganizationDemands(orgId: String): List<Demand>

    suspend fun updateDemandCurrentSum(demandId: String, sum: Double): Unit?

    suspend fun getDemandCurrentSum(demandId: String): Double

    suspend fun getDemandById(demandId: String): Demand?
}