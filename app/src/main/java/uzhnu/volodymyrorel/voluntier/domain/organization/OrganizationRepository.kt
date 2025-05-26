package uzhnu.volodymyrorel.voluntier.domain.organization

import uzhnu.volodymyrorel.voluntier.domain.organization.entity.Organization

interface OrganizationRepository {
    suspend fun getOrganizations(): List<Organization>
}